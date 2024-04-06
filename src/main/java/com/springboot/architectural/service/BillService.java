package com.springboot.architectural.service;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.RegisDto;

import java.util.Date;
import java.util.List;

public interface BillService {
    BillDto getById(int id);
    List<BillDto> getAll(Date from, Date to);
    BillDto add(BillDto billDto);
    BillDto update(BillDto billDto);
    boolean delete(Integer id);

}
