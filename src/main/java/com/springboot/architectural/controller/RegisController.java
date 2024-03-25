package com.springboot.architectural.controller;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.RegisService;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/api/regis")

public class RegisController {
    @Autowired
    RegisService regisService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        if (regisService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found Regis By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(regisService.getById(id));
        responseData.setDesc("Get Regis successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam( required=false)  @DateTimeFormat(pattern="ddMMyyyy") Date from, @RequestParam( required=false)  @DateTimeFormat(pattern="ddMMyyyy") Date to){
        ResponseData responseData = new ResponseData();
        responseData.setData(regisService.getAll(from, to));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RegisDto regis){
        ResponseData responseData = new ResponseData();
        responseData.setData(regisService.add(regis));
        responseData.setDesc("Create regis successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RegisDto regis){
        ResponseData responseData = new ResponseData();
        System.out.println(regis);
        if (regisService.getById(regis.getId()) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Update failed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(regisService.update(regis));
        responseData.setDesc("Update regis successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (regisService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Delete failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(regisService.delete(id));
        responseData.setDesc("Delete Regis successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
