package com.springboot.architectural.service;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomDto;

import java.util.Date;
import java.util.List;

public interface RegisService {
    RegisDto getById(int id);
    List<RegisDto> getAll(Date from, Date to);
    RegisDto add(RegisDto regis);
    RegisDto update(RegisDto regis);
    boolean delete(Integer id);

}
