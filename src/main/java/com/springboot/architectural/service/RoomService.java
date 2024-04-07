package com.springboot.architectural.service;

import com.springboot.architectural.dto.RoomDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    RoomDto getRoomById(int id);
    List<RoomDto> getAllRoom(String searchContent,String type, String disable, String typeSort);
//    List<RoomDto> getAllByType(String searchContent, String type, String typeSort);

    RoomDto addRoom(RoomDto room);
    RoomDto updateRoom(RoomDto room);
    boolean deleteRoom(Integer id);
    boolean uploadImg(MultipartFile multipartFile, Integer room_id);
}
