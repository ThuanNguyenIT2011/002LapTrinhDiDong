package com.springboot.architectural.service;

import com.springboot.architectural.entity.Role;
import com.springboot.architectural.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Optional<Room> getRoomById(int id);
    List<Room> getAllRoom();
    Room addRoom(Room room);
    Room updateRoom(Room room);
    boolean deleteRoom(int id);
}
