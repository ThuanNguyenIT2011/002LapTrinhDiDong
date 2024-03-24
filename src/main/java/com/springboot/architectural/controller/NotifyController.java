package com.springboot.architectural.controller;

import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.exception.NotifyNotfoundException;
import com.springboot.architectural.payload.Request.NotifyRequest;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.imp.NotifyServiceImp;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notify")
public class NotifyController {
    @Autowired
    private NotifyServiceImp notifyService;

    @PostMapping("")
    public ResponseEntity<?> createNotify(@RequestBody NotifyRequest notifyRequest) {
        ResponseData responseData = new ResponseData();
        notifyService.saveNotify(notifyRequest);
        responseData.setData(true);
        responseData.setDesc("Create notify successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
    @PutMapping("")
    public ResponseEntity<?> updateNotify(@RequestBody NotifyRequest notifyRequest) {
        ResponseData responseData = new ResponseData();
        notifyService.saveNotify(notifyRequest);
        responseData.setData(true);
        responseData.setDesc("Update notify successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotify(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();

        try {
            notifyService.deleteNotify(id);
            responseData.setData(true);
        } catch (NotifyNotfoundException e) {
            responseData.setSuccess(false);
            responseData.setData(false);
            responseData.setDesc(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping("")
    public  ResponseEntity<?> getNotifyByUsername(@RequestParam(name = "username") String username){
        ResponseData responseData = new ResponseData();
        List<NotifyDto> notifyDtos = notifyService.getNotifyByUsername(username);
        responseData.setData(notifyDtos);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping("/all")
    public  ResponseEntity<?> getNotify(@RequestParam(defaultValue = "") String disable,
                                        @RequestParam(defaultValue = "DESC") String typeSort,
                                        @RequestParam(defaultValue = "") String searchContent){
        ResponseData responseData = new ResponseData();
        List<NotifyDto> notifyDtos = notifyService.getAllNotify(searchContent, disable, typeSort);
        responseData.setData(notifyDtos);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<?> getNotifyById(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        try {
            NotifyDto notifyDto = notifyService.getNotifyById(id);
            responseData.setData(notifyDto);
        } catch (NotifyNotfoundException e) {
            responseData.setSuccess(false);
            responseData.setData(false);
            responseData.setDesc(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
