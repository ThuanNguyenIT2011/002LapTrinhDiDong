package com.springboot.architectural.service;

import com.springboot.architectural.payload.Request.SignUpRequest;

public interface AccountService {
    boolean addUser(SignUpRequest signUpRequest);
    boolean checkLogin(String userName, String password);
}
