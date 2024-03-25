package com.springboot.architectural.controller;

import com.springboot.architectural.entity.Role;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.payload.Request.RoomDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.RoleService;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")

public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping("/get-room")
    public ResponseEntity<?> getRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (roomService.getRoomById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Not Found Room By ID");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setData(roomService.getRoomById(id));
        responseData.setDesc("Get room successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all-room")
    public ResponseEntity<?> getAllRoom(){
        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.getAllRoom());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto room){
        System.out.println("ROOM: "+room.toString());
        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.addRoom(room));
        responseData.setDesc("Create room successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto room){
        ResponseData responseData = new ResponseData();
        if (roomService.getRoomById(room.getId()) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Update failed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(roomService.updateRoom(room));
        responseData.setDesc("Update room successfully");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoom(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        if (roomService.getRoomById(id) == null)
        {
            responseData.setSuccess(false);
            responseData.setDesc("Delete failed");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setData(roomService.deleteRoom(id));
        responseData.setDesc("Delete room successfully");

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
