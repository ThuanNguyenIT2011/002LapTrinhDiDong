package com.springboot.architectural.service;

import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.dto.RegisDto;

import java.util.Date;
import java.util.List;

public interface ElectricWaterPriceService {
    ElectricWaterPriceDto getById(int id);
    List<ElectricWaterPriceDto> getAll();
    ElectricWaterPriceDto add(ElectricWaterPriceDto electricWaterPriceDto);
    ElectricWaterPriceDto update(ElectricWaterPriceDto electricWaterPriceDto);
    boolean delete(Integer id);

}
