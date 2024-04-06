package com.springboot.architectural.controller;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.BillService;
import com.springboot.architectural.service.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/bill")

public class BillController {
    @Autowired
    BillService billService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        if (billService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found  By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(billService.getById(id));
        responseData.setDesc("Get  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam( required=false)  @DateTimeFormat(pattern="ddMMyyyy") Date from, @RequestParam( required=false)  @DateTimeFormat(pattern="ddMMyyyy") Date to){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.getAll(from, to));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BillDto billDto){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.add(billDto));
        responseData.setDesc("Create bill successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BillDto billDto){
        ResponseData responseData = new ResponseData();
        System.out.println(billDto);
        if (billService.getById(billDto.getId()) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Update failed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(billService.update(billDto));
        responseData.setDesc("Update bill successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (billService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Delete failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(billService.delete(id));
        responseData.setDesc("Delete bill successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
