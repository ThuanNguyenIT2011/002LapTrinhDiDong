package com.springboot.architectural.controller;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.BillDtoSmall;
import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.BillService;
import com.springboot.architectural.service.BillService2;
import com.springboot.architectural.service.ElectricWaterPriceService;
import com.springboot.architectural.service.ElectricWaterPriceService2;
import com.springboot.architectural.service.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bill2")

public class BillController2 {
    @Autowired
    BillService billService;
    @Autowired
    BillService2 billService2;

    @Autowired
    ElectricWaterPriceService2 electricWaterPriceService2;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        ResponseData responseData = new ResponseData();
        List<BillDto> billsDto = billService2.getAll();
        List<BillDtoSmall> billsSmall = new ArrayList<>();
        for (BillDto billDto : billsDto) {
            billsSmall.add(BillDtoSmall.BillDto2Small(billDto));
        }
        
        responseData.setData(billsSmall);
        responseData.setDesc("Get all successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/create/electric-water")
    public ResponseEntity<?> create(@RequestBody ElectricWaterPriceDto electricWaterPriceDto){
        ResponseData responseData = new ResponseData();

        ElectricWaterPriceDto electricWaterPriceDto2 = electricWaterPriceService2.add(electricWaterPriceDto);
        if(electricWaterPriceDto2 == null){
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
        electricWaterPriceDto2.setRoomName(electricWaterPriceDto.getRoomName());
        // System.out.println("\n\n\n\n" + createElectricWaterPriceBillDto(electricWaterPriceDto2).toString() + "\n\n\n\n");
        try {
        BillDto billDto = billService.add(createElectricWaterPriceBillDto(electricWaterPriceDto2));

        responseData.setData(billService.add(billDto));
        responseData.setDesc("Create bill successfully");
        } catch (Exception e) {
            System.out.println("\n\n\n\n" + e.getMessage() + "\n\n\n\n");
        }
       
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/pay")
    public ResponseEntity<?> payBill(@RequestParam(name = "id") int id){
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

    private BillDto createElectricWaterPriceBillDto(ElectricWaterPriceDto electricWaterPriceDto){
        BillDto billDto = new BillDto();
        billDto.setId(0);
        billDto.setContent("Tien dien nuoc");
        billDto.setTitle("HK2 - 2024");
        billDto.setCreateAt(new Date());
        billDto.setElectricWaterPriceId(electricWaterPriceDto.getId().toString());
        billDto.setPay(false);
        billDto.setAcceptBy(electricWaterPriceDto.getRoomName());
        return billDto;
    }
    
}
