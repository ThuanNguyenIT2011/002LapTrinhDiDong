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
    @GetMapping("/get-summary-student")
    public ResponseEntity<?> getSummaryStudent(@RequestParam  @DateTimeFormat(pattern="ddMMyyyy") Date from, @RequestParam   @DateTimeFormat(pattern="ddMMyyyy") Date to, @RequestParam(defaultValue = "") String typeRoom){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.summaryStudent(from, to, typeRoom));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData.getData(), HttpStatus.OK);
    }

    @GetMapping("/get-summary-electric-water-price")
    public ResponseEntity<?> getSummaryElectricWaterPrice(@RequestParam  @DateTimeFormat(pattern="ddMMyyyy") Date from, @RequestParam   @DateTimeFormat(pattern="ddMMyyyy") Date to, @RequestParam(defaultValue = "") String typeRoom){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.summaryElectricWaterPrice(from, to, typeRoom));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData.getData(), HttpStatus.OK);
    }
    @GetMapping("/get-summary-boarding-price")
    public ResponseEntity<?> getSummaryBoarding(@RequestParam  @DateTimeFormat(pattern="ddMMyyyy") Date from, @RequestParam   @DateTimeFormat(pattern="ddMMyyyy") Date to, @RequestParam(defaultValue = "") String typeRoom){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.summaryBoardingPrice(from, to, typeRoom));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData.getData(), HttpStatus.OK);
    }
    @GetMapping("/get-all-by-student")
    public ResponseEntity<?> getAllByStudentId(@RequestParam String studentId){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.getAllByStudent(studentId));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get-all-by-roomId")
    public ResponseEntity<?> getAllByRoomID(@RequestParam Integer roomId){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.getAllByRoomId(roomId));
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get-all-by-room")
    public ResponseEntity<?> getAllByRoom(){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.getAllByRoom());
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get-all-by-electric-water")
    public ResponseEntity<?> getAllByElectricWater(){
        ResponseData responseData = new ResponseData();
        responseData.setData(billService.getAllByElectricWater());
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
    public ResponseEntity<?> delete(@RequestParam int id){
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

    @PutMapping("/pay")
    public ResponseEntity<?> payBill(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (billService.getById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Pay failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(billService.payBill(id));
        responseData.setDesc("Pay bill successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
