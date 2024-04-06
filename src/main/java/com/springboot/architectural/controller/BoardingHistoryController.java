package com.springboot.architectural.controller;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.BillService;
import com.springboot.architectural.service.BoardingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/boarding-history")

public class BoardingHistoryController {
    @Autowired
    BoardingHistoryService boardingHistoryService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        if (boardingHistoryService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found  By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(boardingHistoryService.getById(id));
        responseData.setDesc("Get  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        ResponseData responseData = new ResponseData();
        responseData.setData(boardingHistoryService.getAll());
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BoardingHistoryDto boardingHistoryDto){
        ResponseData responseData = new ResponseData();
        responseData.setData(boardingHistoryService.add(boardingHistoryDto));
        responseData.setDesc("Create  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BoardingHistoryDto boardingHistoryDto){
        ResponseData responseData = new ResponseData();
        System.out.println(boardingHistoryDto);
        if (boardingHistoryService.getById(boardingHistoryDto.getId()) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Update failed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(boardingHistoryService.update(boardingHistoryDto));
        responseData.setDesc("Update  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (boardingHistoryService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Delete failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(boardingHistoryService.delete(id));
        responseData.setDesc("Deleted successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
