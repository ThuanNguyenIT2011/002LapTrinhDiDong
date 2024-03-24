package com.springboot.architectural.service;

import com.springboot.architectural.payload.Request.SignUpRequest;

public interface LoginService {
    boolean addUser(SignUpRequest signUpRequest);
    boolean checkLogin(String userName, String password);

    String login(String userName, String password);
}
