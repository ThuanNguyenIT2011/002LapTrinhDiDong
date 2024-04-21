package com.springboot.architectural.service;

import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.payload.Request.AccountRequest;
import com.springboot.architectural.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;


public interface AccountService {
    AccountInfoDto updateAccount(AccountRequest accountRequest) throws AccountNotFoundException;
}
