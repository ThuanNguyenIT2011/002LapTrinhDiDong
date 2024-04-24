package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.InforDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.exception.ItemNotfoundException;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.service.InforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class InforServiceImp implements InforService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public InforDto updateInfor(String firstName, String lastName, String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()){
            return null;
        }
        Account account = accountOptional.get();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        try {
            accountRepository.save(account);
//            String bodyMail = "Code: " + codeVerify;
//            emailSenderService.sendSimpleEmail(signUpRequest.getUsername()+ "@student.ptithcm.edu.vn",
//                    "verification_code", bodyMail);
            return new InforDto(firstName, lastName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }


    }
}
