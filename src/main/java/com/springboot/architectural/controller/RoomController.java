package com.springboot.architectural.controller;

import com.springboot.architectural.entity.Role;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.RoleService;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping("/get-room")
    public ResponseEntity<?> getRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (roomService.getRoomById(id).isEmpty())
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found Room By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(roomService.getRoomById(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all-room")
    public ResponseEntity<?> getAllRoom(){
        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.getAllRoom());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody Room room){
        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.addRoom(room));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateRoom(@RequestBody Room room){
        ResponseData responseData = new ResponseData();
        if (roomService.getRoomById(room.getId()).isEmpty())
        {
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(roomService.updateRoom(room));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestBody Room room){
        ResponseData responseData = new ResponseData();
        if (roomService.getRoomById(room.getId()).isEmpty())
        {
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(roomService.deleteRoom(room.getId()));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
