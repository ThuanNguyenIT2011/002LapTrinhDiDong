package com.springboot.architectural.controller;

import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.BoardingHistoryService;
import com.springboot.architectural.service.ElectricWaterPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/electric-water-price")

public class ElectricWaterPriceController {
    @Autowired
    ElectricWaterPriceService electricWaterPriceService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        if (electricWaterPriceService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found  By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(electricWaterPriceService.getById(id));
        responseData.setDesc("Get  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        ResponseData responseData = new ResponseData();
        responseData.setData(electricWaterPriceService.getAll());
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ElectricWaterPriceDto electricWaterPriceDto){
        ResponseData responseData = new ResponseData();
        responseData.setData(electricWaterPriceService.add(electricWaterPriceDto));
        responseData.setDesc("Create  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ElectricWaterPriceDto electricWaterPriceDto){
        ResponseData responseData = new ResponseData();
        System.out.println(electricWaterPriceDto);
        if (electricWaterPriceService.getById(electricWaterPriceDto.getId()) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Update failed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(electricWaterPriceService.update(electricWaterPriceDto));
        responseData.setDesc("Update  successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (electricWaterPriceService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Delete failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(electricWaterPriceService.delete(id));
        responseData.setDesc("Deleted successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
