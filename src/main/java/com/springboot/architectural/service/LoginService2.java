package com.springboot.architectural.service;

import com.springboot.architectural.dto.AccountDto;
import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.exception.UsernameIsExistException;
import com.springboot.architectural.payload.Request.SignUpRequest;

import javax.security.auth.login.AccountNotFoundException;

public interface LoginService2 {
    AccountDto addUser(SignUpRequest signUpRequest) throws UsernameIsExistException;
}
