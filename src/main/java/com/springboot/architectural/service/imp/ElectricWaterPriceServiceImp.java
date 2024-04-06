package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.RoomRegis;
import com.springboot.architectural.mapper.ElectricWaterPriceMapper;
import com.springboot.architectural.mapper.RegisMapper;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.ElectricWaterPriceRepository;
import com.springboot.architectural.repository.RegisRepository;
import com.springboot.architectural.repository.RoomRegisRepository;
import com.springboot.architectural.service.ElectricWaterPriceService;
import com.springboot.architectural.service.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectricWaterPriceServiceImp implements ElectricWaterPriceService {
    @Autowired
    private ElectricWaterPriceRepository electricWaterPriceRepository;
    @Autowired
    private RoomRegisRepository roomRegisRepository;

    @Override
    public ElectricWaterPriceDto getById(int id) {
        Optional<ElectricWaterPrice> electricWaterPrice = electricWaterPriceRepository.findById(id);
        return electricWaterPrice.map(ElectricWaterPriceMapper.INSTANCE::electricWaterPriceToElectricWaterPriceDto).orElse(null);
    }

    @Override
    public List<ElectricWaterPriceDto> getAll() {
        List<ElectricWaterPrice> electricWaterPriceList = electricWaterPriceRepository.findAll();
        return electricWaterPriceList.stream().map(ElectricWaterPriceMapper.INSTANCE::electricWaterPriceToElectricWaterPriceDto).collect(Collectors.toList());
    }

    @Override
    public ElectricWaterPriceDto add(ElectricWaterPriceDto electricWaterPriceDto) {
        System.out.println(electricWaterPriceDto);

        ElectricWaterPrice entity = ElectricWaterPriceMapper.INSTANCE.electricWaterPriceDtoToElectricWaterPrice(electricWaterPriceDto);
        Optional<RoomRegis> roomRegis = roomRegisRepository.findById(electricWaterPriceDto.getRoomRegisId());
        if (roomRegis.isEmpty()) return null;
        entity.setRoomRegis(roomRegis.get());
//        System.out.println(entity);
        return  ElectricWaterPriceMapper.INSTANCE.electricWaterPriceToElectricWaterPriceDto(electricWaterPriceRepository.save(entity));
    }

    @Override
    public ElectricWaterPriceDto update(ElectricWaterPriceDto electricWaterPriceDto) {
        if (electricWaterPriceDto.getId() == null) return null;
        Optional<RoomRegis> roomRegis = roomRegisRepository.findById(electricWaterPriceDto.getRoomRegisId());
        if (roomRegis.isEmpty()) return null;
        ElectricWaterPrice entity = ElectricWaterPriceMapper.INSTANCE.electricWaterPriceDtoToElectricWaterPrice(electricWaterPriceDto);
        entity.setRoomRegis(roomRegis.get());
        return  ElectricWaterPriceMapper.INSTANCE.electricWaterPriceToElectricWaterPriceDto(electricWaterPriceRepository.save(entity));
    }

    @Override
    public boolean delete(Integer id) {
        Optional<ElectricWaterPrice> r = electricWaterPriceRepository.findById(id);
        if (r.isPresent())
        {
            electricWaterPriceRepository.delete(r.get());
            return true;
        }
        return false;
    }


}
