package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.mapper.RegisMapper;
import com.springboot.architectural.mapper.RoomMapper;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.RegisRepository;
import com.springboot.architectural.repository.RoomRepository;
import com.springboot.architectural.service.FileService;
import com.springboot.architectural.service.RegisService;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisServiceImp implements RegisService {
    @Autowired
    private RegisRepository regisRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public RegisDto getById(int id) {
        Optional<Regis> regis = regisRepository.findById(id);
        if (!regis.isPresent()) return  null;
        return RegisMapper.INSTANCE.regisToRegisDto(regis.get());
    }

    @Override
    public List<RegisDto> getAll(Date from, Date to) {
        List<Regis> regises = new ArrayList<>();
        if (from == null && to == null)
            regises = regisRepository.findAll();
        else if (from == null) regises = regisRepository.findByEndRegisAtBefore(to);
        else if (to == null) regises = regisRepository.findByEndRegisAtAfter(from);
        else regises = regisRepository.findByEndRegisAtBetween(from, to);
        return regises.stream().map(RegisMapper.INSTANCE::regisToRegisDto).collect(Collectors.toList());
    }

    @Override
    public RegisDto add(RegisDto regis) {
        Regis regisEntity = RegisMapper.INSTANCE.regisDtoToRegis(regis);
        regisEntity.setAccount(accountRepository.findByUsername(regis.getCreateBy()).get());
        return  RegisMapper.INSTANCE.regisToRegisDto(regisRepository.save(regisEntity));
    }

    @Override
    public RegisDto update(RegisDto regis) {
        if (regis.getId() == null) return null;
        Optional<Regis> regisCheck = regisRepository.findById(regis.getId());
        Optional<Account> accountCheck = accountRepository.findById(regis.getCreateBy());
        if (!regisCheck.isPresent() ||  !accountCheck.isPresent()) return  null;
        Regis regisEntity = RegisMapper.INSTANCE.regisDtoToRegis(regis);
        regisEntity.setAccount(accountCheck.get());
        return  RegisMapper.INSTANCE.regisToRegisDto(regisRepository.save(regisEntity));
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Regis> r = regisRepository.findById(id);
        if (r.isPresent())
        {
            regisRepository.delete(r.get());
            return true;
        }
        return false;
    }


}
