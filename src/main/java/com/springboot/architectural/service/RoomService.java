package com.springboot.architectural.service;

import com.springboot.architectural.payload.Request.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto getRoomById(int id);
    List<RoomDto> getAllRoom();
    RoomDto addRoom(RoomDto room);
    RoomDto updateRoom(RoomDto room);
    boolean deleteRoom(int id);
}
