package com.springboot.architectural.controller;

import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.exception.UsernameIsExistException;
import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.LoginService;
import com.springboot.architectural.service.LoginService2;
import com.springboot.architectural.service.imp.EmailSenderService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/login")
public class LoginController2 {
    @Autowired
    private LoginService2 loginService2;

    @PostMapping("/signup2")
    public ResponseEntity<?> signup(@RequestBody @NonNull SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        try {
            responseData.setData(loginService2.addUser(signUpRequest));
        } catch (UsernameIsExistException e) {
           responseData.setSuccess(false);
           responseData.setDesc(e.getMessage());
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
