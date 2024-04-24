package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.ChartDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.*;
import com.springboot.architectural.mapper.BillMapper;
import com.springboot.architectural.mapper.BoardingHistoryMapper;
import com.springboot.architectural.mapper.RegisMapper;
import com.springboot.architectural.repository.*;
import com.springboot.architectural.service.BillService;
import com.springboot.architectural.service.BillService2;
import com.springboot.architectural.service.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImp2 implements BillService2 {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private BoardingHistoryRepository boardingHistoryRepository;
    @Autowired
    private ElectricWaterPriceRepository electricWaterPriceRepository;

    @Override
    public List<BillDto> getAll() {
        List<Bill> bills = new ArrayList<>();
        bills = billRepository.findAll();
        return bills.stream().map(BillMapper.INSTANCE::billToBillDto).collect(Collectors.toList());
    }

}
