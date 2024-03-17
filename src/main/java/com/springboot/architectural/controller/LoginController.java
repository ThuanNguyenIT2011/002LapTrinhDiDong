package com.springboot.architectural.controller;

import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.AccountService;
import com.springboot.architectural.until.UtilsJwtHelper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UtilsJwtHelper utilsJwtHelper;// = new UtilsJwtHelper();

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();

        responseData.setData(accountService.addUser(signUpRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password) {
        ResponseData responseData = new ResponseData();

        if(accountService.checkLogin(username, password)) {
            System.out.println("Hello");
            String token = utilsJwtHelper.generateToken(username);
            responseData.setData(token);
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
