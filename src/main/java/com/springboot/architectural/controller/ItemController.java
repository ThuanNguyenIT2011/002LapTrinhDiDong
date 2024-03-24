package com.springboot.architectural.controller;


import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<?> createItem(@RequestParam(name = "fileUpload")MultipartFile multipartFile) {
        ResponseData responseData = new ResponseData();


        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
