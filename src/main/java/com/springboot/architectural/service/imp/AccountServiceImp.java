package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.mapper.AccountInfoMapper;
import com.springboot.architectural.payload.Request.AccountRequest;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountInfoDto updateAccount(AccountRequest accountRequest) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(accountRequest.getUsername());

        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("Username not found");
        }

        Account account = accountOptional.get();

        account.setLastName(accountRequest.getLastName());
        account.setFirstName(accountRequest.getFirstName());

        AccountInfoDto accountInfoDto = AccountInfoMapper.mapToAccountInfo(account);

        accountInfoDto.setToken(accountRequest.getToken());

        accountRepository.save(account);


        return accountInfoDto;
    }
}
