package com.springboot.architectural.controller;

import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.exception.UsernameIsExistException;
import com.springboot.architectural.payload.Request.SignUpRequest;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.LoginService;
import com.springboot.architectural.service.imp.EmailSenderService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @NonNull SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        try {
            responseData.setData(loginService.addUser(signUpRequest));
        } catch (UsernameIsExistException e) {
           responseData.setSuccess(false);
           responseData.setDesc(e.getMessage());
        }


        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password) {
        ResponseData responseData = new ResponseData();

        System.out.println(username);
        System.out.println(password);

        AccountInfoDto accountInfoDto =loginService.checkLogin(username, password);

        if(accountInfoDto != null) {
            String token = loginService.login(username, password);
            accountInfoDto.setToken(token);
            responseData.setData(accountInfoDto);
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
            responseData.setStatus(401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/forget")
    public ResponseEntity<?> forget(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "role") String role) {
        ResponseData responseData = new ResponseData();
        boolean checkForget =loginService.checkForget(username, role);
        if(checkForget) {
            responseData.setData("");
            responseData.setSuccess(true);
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
            responseData.setStatus(401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam(name = "username") String username,
                                            @RequestParam(name = "password") String password
    ) {
        ResponseData responseData = new ResponseData();
        boolean checkForget =loginService.updatePassword(username, password);
        if(checkForget) {
            responseData.setData("");
            responseData.setSuccess(true);
        } else {
            responseData.setData("");
            responseData.setSuccess(false);
            responseData.setStatus(401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "code") String code) {
        ResponseData responseData = new ResponseData();

        try {
            boolean isVerify = loginService.verifyCode(code, username);
            responseData.setData(isVerify);
        } catch (AccountNotFoundException e) {
            responseData.setData(e.getMessage());
            responseData.setSuccess(false);

        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
