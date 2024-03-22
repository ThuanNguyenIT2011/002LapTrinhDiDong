package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Role;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.repository.RoomRepository;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Optional<Room> getRoomById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public boolean deleteRoom(int id) {
        Optional<Room> r = roomRepository.findById(id);
        if (r.isPresent())
        {
            roomRepository.delete(r.get());
            return true;
        }
        return false;
    }
}
