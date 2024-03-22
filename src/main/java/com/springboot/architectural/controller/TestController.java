package com.springboot.architectural.controller;

import com.springboot.architectural.payload.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PreAuthorize("hasAuthority('MANAGER')")
public class TestController {


//    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        ResponseData responseData = new ResponseData();
        responseData.setData("Hello");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
