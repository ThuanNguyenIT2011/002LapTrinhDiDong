package com.springboot.architectural.controller;

import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();

        responseData.setData(loginService.addUser(signUpRequest));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password) {
        ResponseData responseData = new ResponseData();

        if(loginService.checkLogin(username, password)) {
            System.out.println("Hello");
//            String token = utilsJwtHelper.generateToken(username);
            String token = loginService.login(username, password);
            responseData.setData(token);
        } else {
            System.out.println("Hello2");
            responseData.setData("");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
