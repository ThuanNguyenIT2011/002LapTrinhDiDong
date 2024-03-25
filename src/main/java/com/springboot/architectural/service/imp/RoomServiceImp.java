package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.mapper.RoomMapper;
import com.springboot.architectural.payload.Request.RoomDto;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.RoomRepository;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public RoomDto getRoomById(int id) {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent()) return  null;
        return RoomMapper.INSTANCE.roomToRoomDto(room.get());
    }

    @Override
    public List<RoomDto> getAllRoom() {
        return roomRepository.findAll().stream().map((room) ->  RoomMapper.INSTANCE.roomToRoomDto(room)).collect(Collectors.toList());
    }

    @Override
    public RoomDto addRoom(RoomDto room) {
        Room roomEntity = RoomMapper.INSTANCE.roomDtotoRoom(room);
        roomEntity.setAccount(accountRepository.findByUsername(room.getCreate_by()).get());
        return  RoomMapper.INSTANCE.roomToRoomDto(roomRepository.save(roomEntity));
    }

    @Override
    public RoomDto updateRoom(RoomDto room) {
        if (room.getId() == null) return null;
        Optional<Room> roomCheck = roomRepository.findById(room.getId());
        Optional<Account> accountCheck = accountRepository.findById(room.getCreate_by());
        if (!roomCheck.isPresent() ||  !accountCheck.isPresent()) return  null;
        Room roomEntity = RoomMapper.INSTANCE.roomDtotoRoom(room);
        roomEntity.setAccount(accountCheck.get());
        return  RoomMapper.INSTANCE.roomToRoomDto(roomRepository.save(roomEntity));
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
