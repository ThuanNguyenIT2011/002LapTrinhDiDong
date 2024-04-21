package com.springboot.architectural.service;

import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.payload.Request.SignUpRequest;

import javax.security.auth.login.AccountNotFoundException;

public interface LoginService {
    boolean addUser(SignUpRequest signUpRequest);
    AccountInfoDto checkLogin(String userName, String password);

    String login(String userName, String password);

    boolean verifyCode(String code, String username) throws AccountNotFoundException;
}
