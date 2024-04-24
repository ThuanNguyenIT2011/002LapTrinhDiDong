package com.springboot.architectural.controller;



import com.springboot.architectural.dto.InforDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.InforService;
import com.springboot.architectural.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/infor")
public class InforController {

    @Autowired
    private InforService inforService;

    @PutMapping("/update")
    public ResponseEntity<?> signup(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("username") String username) {
        ResponseData responseData = new ResponseData();
        InforDto inforDto = inforService.updateInfor(firstName, lastName, username);
        if (inforDto != null){
            responseData.setData(inforDto);
        }
        else{
            responseData.setData("");
            responseData.setSuccess(false);
            responseData.setStatus(401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }


        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    
}
