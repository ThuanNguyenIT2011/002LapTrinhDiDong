package com.springboot.architectural.service;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomRegisDto;

import java.util.Date;
import java.util.List;

public interface RoomRegisService {
    RoomRegisDto getById(int id);
    List<RoomRegisDto> getAll();
    RoomRegisDto add(RoomRegisDto roomRegis);
    RoomRegisDto update(RoomRegisDto roomRegis);
    boolean delete(Integer id);

}
