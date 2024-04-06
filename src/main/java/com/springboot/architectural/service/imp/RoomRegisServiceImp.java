package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomRegisDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.entity.RoomRegis;
import com.springboot.architectural.mapper.RegisMapper;
import com.springboot.architectural.mapper.RoomRegisMapper;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.RegisRepository;
import com.springboot.architectural.repository.RoomRegisRepository;
import com.springboot.architectural.repository.RoomRepository;
import com.springboot.architectural.service.RoomRegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomRegisServiceImp implements RoomRegisService {
    @Autowired
    private RoomRegisRepository roomRegisRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RegisRepository regisRepository;

    @Override
    public RoomRegisDto getById(int id) {
        Optional<RoomRegis> roomRegis = roomRegisRepository.findById(id);
        if (!roomRegis.isPresent()) return  null;
        return RoomRegisMapper.INSTANCE.roomRegisToRoomRegisDto(roomRegis.get());
    }

    public List<RoomRegisDto> getAll() {
        List<RoomRegis> roomRegises = roomRegisRepository.findAll();
        return roomRegises.stream().map(RoomRegisMapper.INSTANCE::roomRegisToRoomRegisDto).collect(Collectors.toList());
    }

    @Override
    public RoomRegisDto add(RoomRegisDto roomRegis) {
        System.out.println(roomRegis);
        RoomRegis roomRegisEntity = RoomRegisMapper.INSTANCE.roomRegisDtoToRoomRegis(roomRegis);
        if (roomRegis.getRegisId() == null || roomRegis.getRoomId() == null  ) return null;
        Optional<Room> room = roomRepository.findById(roomRegis.getRoomId());
        Optional<Regis> regis = regisRepository.findById(roomRegis.getRegisId());
        if (room.isEmpty() || regis.isEmpty()) return null;
        roomRegisEntity.setRoom(room.get());
        roomRegisEntity.setRegis(regis.get());
        return  RoomRegisMapper.INSTANCE.roomRegisToRoomRegisDto(roomRegisRepository.save(roomRegisEntity));
    }

    @Override
    public RoomRegisDto update(RoomRegisDto roomRegis) {
        System.out.println(roomRegis);
        if (roomRegis.getId() == null) return null;
        System.out.println(1);

        Optional<RoomRegis> checkRR = roomRegisRepository.findById(roomRegis.getId());
        if (checkRR.isEmpty()) return  null;

        if (roomRegis.getRegisId() == null || roomRegis.getRoomId() == null  ) return null;
        Optional<Room> room = roomRepository.findById(roomRegis.getRegisId());
        Optional<Regis> regis = regisRepository.findById(roomRegis.getRoomId());

        if (room.isEmpty() || regis.isEmpty()) return null;

        RoomRegis roomRegisEntity = RoomRegisMapper.INSTANCE.roomRegisDtoToRoomRegis(roomRegis);
        roomRegisEntity.setRoom(room.get());
        roomRegisEntity.setRegis(regis.get());

        return  RoomRegisMapper.INSTANCE.roomRegisToRoomRegisDto(roomRegisRepository.save(roomRegisEntity));
    }

    @Override
    public boolean delete(Integer id) {
        Optional<RoomRegis> r = roomRegisRepository.findById(id);
        if (r.isPresent())
        {
            roomRegisRepository.delete(r.get());
            return true;
        }
        return false;
    }


}
