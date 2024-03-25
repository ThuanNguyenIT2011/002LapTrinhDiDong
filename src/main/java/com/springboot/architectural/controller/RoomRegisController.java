package com.springboot.architectural.controller;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomDto;
import com.springboot.architectural.dto.RoomRegisDto;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.entity.RoomRegis;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.RegisService;
import com.springboot.architectural.service.RoomRegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/room-regis")

public class RoomRegisController {
    @Autowired
    RoomRegisService romRegisService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        if (romRegisService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found room regis By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(romRegisService.getById(id));
        responseData.setDesc("Get room regis successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam( required=false)  @DateTimeFormat(pattern="ddMMyyyy") Date from, @RequestParam( required=false)  @DateTimeFormat(pattern="ddMMyyyy") Date to){
        ResponseData responseData = new ResponseData();
        responseData.setData(romRegisService.getAll());
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoomRegisDto roomRegis){
        ResponseData responseData = new ResponseData();
        RoomRegisDto r = romRegisService.add(roomRegis);
        responseData.setData(r);
        responseData.setDesc("Create room regis successfully");

        if (r == null)
        {
            responseData.setDesc("Create room regis failed");

            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RoomRegisDto roomRegis){
        ResponseData responseData = new ResponseData();
        System.out.println(roomRegis);
        RoomRegisDto r = romRegisService.update(roomRegis);
        if (r == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Update failed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(romRegisService.update(roomRegis));
        responseData.setDesc("Update room regis successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (romRegisService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Delete failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(romRegisService.delete(id));
        responseData.setDesc("Delete Regis successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
