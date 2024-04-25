package com.springboot.architectural.controller;

import com.springboot.architectural.dto.RoomDto;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/room")

public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoom(@PathVariable(name = "id") Integer id){
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

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllRoom(@RequestParam(defaultValue = "") String disable,
                                        @RequestParam(defaultValue = "") String typeRoom,
                                        @RequestParam(defaultValue = "DESC") String typeSort,
                                        @RequestParam(defaultValue = "") String searchContent){

        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.getAllRoom(searchContent,typeRoom, disable, typeSort));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto room){
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

    @PostMapping("/upload")
    public ResponseEntity<?> deleteRoom(@RequestParam(name = "fileUpload")MultipartFile multipartFile, @RequestParam(name= "room_id")Integer room_id){
        ResponseData responseData = new ResponseData();
        if (roomService.uploadImg(multipartFile, room_id))
        {
            responseData.setData(true);
            responseData.setDesc("Upload room successfully");
        }
        else
        {
            responseData.setData(false);
            responseData.setDesc("Upload failed");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all-res")
    public ResponseEntity<?> getAllRoomRegister(@RequestParam(defaultValue = "") String status,
                                                @RequestParam(defaultValue = "") String searchContent,
                                                @RequestParam(defaultValue = "") String type){

        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.getAllRoomForRegister(status, searchContent, type));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/get-room-regis/{id}")
    public ResponseEntity<?> getRoomRegis(@PathVariable(name = "id")Integer id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(roomService.getRoomByIdByRegister(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
