package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.*;
import com.springboot.architectural.mapper.BillMapper;
import com.springboot.architectural.mapper.BoardingHistoryMapper;
import com.springboot.architectural.mapper.RegisMapper;
import com.springboot.architectural.repository.*;
import com.springboot.architectural.service.BillService;
import com.springboot.architectural.service.RegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BoardingHistoryRepository boardingHistoryRepository;

    @Autowired
    private ElectricWaterPriceRepository electricWaterPriceRepository;


    @Override
    public BillDto getById(int id) {
        Optional<Bill> bill = billRepository.findById(id);
        return bill.map(BillMapper.INSTANCE::billToBillDto).orElse(null);
    }

    @Override
    public List<BillDto> getAll(Date from, Date to) {
        List<Bill> bills = new ArrayList<>();
        if (from == null && to == null)
            bills = billRepository.findAll();
        else if (from == null) bills = billRepository.findByCreateAtBefore(to);
        else if (to == null) bills = billRepository.findByCreateAtAfter(from);
        else bills = billRepository.findByCreateAtBetween(from, to);
        return bills.stream().map(BillMapper.INSTANCE::billToBillDto).collect(Collectors.toList());
    }

    public List<BillDto> getAllByStudent(String studentId) {
        List<Bill> bills = billRepository.findAllByStudent(studentId);
        return bills.stream().map(BillMapper.INSTANCE::billToBillDto).collect(Collectors.toList());
    }

    @Override
    public List<BillDto> getAllByRoomId(Integer roomID) {
        List<Bill> bills = billRepository.findAllByRoomId(roomID);
        return bills.stream().map(BillMapper.INSTANCE::billToBillDto).collect(Collectors.toList());
    }

    @Override
    public List<BillDto> getAllByRoom()
    {
        List<Bill> bills = billRepository.findAllByRoom();
        return bills.stream().map(BillMapper.INSTANCE::billToBillDto).collect(Collectors.toList());
    }
    @Override
    public List<BillDto> getAllByElectricWater(){
        List<Bill> bills = billRepository.findAllByElectricWater();
        return bills.stream().map(BillMapper.INSTANCE::billToBillDto).collect(Collectors.toList());
    }
    @Override
    public BillDto add(BillDto billDto) {
        Bill entity = BillMapper.INSTANCE.billDtoToBill(billDto);
        if (billDto.getBoardingId() != null) {
            Optional<BoardingHistory> boardingHistory = boardingHistoryRepository.findById(Integer.valueOf(billDto.getBoardingId()));
            if (boardingHistory.isEmpty()) return null;
            Integer price = boardingHistory.get().getRoomRegis().getRegis().getRoomPriceVND();
            entity.setPrice(price);
            entity.setBoardingHistory(boardingHistory.get());
        }
        if (billDto.getElectricWaterPriceId() != null) {
            Optional<ElectricWaterPrice> electricWaterPrice = electricWaterPriceRepository.findById(Integer.valueOf(billDto.getElectricWaterPriceId()));
            if (electricWaterPrice.isEmpty()) return null;
            Integer uE = electricWaterPrice.get().getElectricUnitPrice();
            Integer mE = electricWaterPrice.get().getElectricKg();
            Integer uW = electricWaterPrice.get().getWaterUnitPrice();
            Integer mW = electricWaterPrice.get().getWaterM3();
            Integer price = uE * mE + uW + mW;
            entity.setPrice(price);
            entity.setElectricWaterPrice(electricWaterPrice.get());

        }
        Optional<Account> account = accountRepository.findById(billDto.getAcceptBy());
        if (account.isEmpty()) return null;
        entity.setAccount(account.get());
        return BillMapper.INSTANCE.billToBillDto(billRepository.save(entity));
    }

    @Override
    public boolean payBill(Integer id)
    {
        Optional<Bill> billCheck = billRepository.findById(id);
        if (billCheck.isEmpty()) return  false;
        billCheck.get().setPay(true);
        billRepository.save(billCheck.get());
        return true;
    }

    @Override
    public BillDto update(BillDto billDto) {
        if (billDto.getId() == null) return null;
        Optional<Bill> billCheck = billRepository.findById(billDto.getId());
        if (billCheck.isEmpty()) return  null;
        Bill entity = BillMapper.INSTANCE.billDtoToBill(billDto);
        if (billDto.getBoardingId() != null) {
            Optional<BoardingHistory> boardingHistory = boardingHistoryRepository.findById(Integer.valueOf(billDto.getBoardingId()));
            if (boardingHistory.isEmpty()) return  null;
            Integer price = boardingHistory.get().getRoomRegis().getRegis().getRoomPriceVND();
            entity.setPrice(price);
            entity.setBoardingHistory(boardingHistory.get());
        }
        if (billDto.getElectricWaterPriceId() != null)
        {
            Optional<ElectricWaterPrice>  electricWaterPrice = electricWaterPriceRepository.findById(Integer.valueOf(billDto.getElectricWaterPriceId()));
            if (electricWaterPrice.isEmpty()) return  null;
            Integer uE = electricWaterPrice.get().getElectricUnitPrice();
            Integer mE = electricWaterPrice.get().getElectricKg();
            Integer uW = electricWaterPrice.get().getWaterUnitPrice();
            Integer mW = electricWaterPrice.get().getWaterM3();
            Integer price = uE*mE + uW+mW;
            entity.setPrice(price);
            entity.setElectricWaterPrice(electricWaterPrice.get());

        }
        Optional<Account> account = accountRepository.findById(billDto.getAcceptBy());
        if (account.isEmpty()) return null;
        entity.setAccount(account.get());
        return  BillMapper.INSTANCE.billToBillDto(billRepository.save(entity));
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Bill> r = billRepository.findById(id);
        if (r.isPresent())
        {
            billRepository.delete(r.get());
            return true;
        }
        return false;
    }


}
