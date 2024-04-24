package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.*;
import com.springboot.architectural.mapper.ElectricWaterPriceMapper;
import com.springboot.architectural.mapper.RegisMapper;
import com.springboot.architectural.repository.*;
import com.springboot.architectural.service.ElectricWaterPriceService;
import com.springboot.architectural.service.ElectricWaterPriceService2;
import com.springboot.architectural.service.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectricWaterPriceServiceImp2 implements ElectricWaterPriceService2 {
    @Autowired
    private ElectricWaterPriceRepository electricWaterPriceRepository;
    @Autowired
    private RoomRegisRepository roomRegisRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public ElectricWaterPriceDto add(ElectricWaterPriceDto electricWaterPriceDto) {
        System.out.println(electricWaterPriceDto);

        ElectricWaterPrice entity = ElectricWaterPriceMapper.INSTANCE.electricWaterPriceDtoToElectricWaterPrice(electricWaterPriceDto);
        Optional<RoomRegis> roomRegis = roomRegisRepository.findById(electricWaterPriceDto.getRoomRegisId());
        if (roomRegis.isEmpty()) return null;
        entity.setRoomRegis(roomRegis.get());
        ElectricWaterPriceDto electricWaterPriceDto2 = ElectricWaterPriceMapper.INSTANCE.electricWaterPriceToElectricWaterPriceDto(electricWaterPriceRepository.save(entity));
        //        System.out.println(entity);
        electricWaterPriceDto2.setId(entity.getId());
        return  electricWaterPriceDto2;
    }


}
