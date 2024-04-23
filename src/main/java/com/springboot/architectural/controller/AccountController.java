package com.springboot.architectural.controller;

import com.springboot.architectural.payload.Request.AccountRequest;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PutMapping("/update_info")
    public ResponseEntity<?> updateInfoAccount(@RequestBody AccountRequest accountRequest) {
        ResponseData responseData = new ResponseData();

        try {
            responseData.setData(accountService.updateAccount(accountRequest));
        } catch (AccountNotFoundException e) {
            responseData.setData(e.getMessage());
            responseData.setSuccess(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
