package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.BoardingHistory;
import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.RoomRegis;
import com.springboot.architectural.mapper.BoardingHistoryMapper;
import com.springboot.architectural.mapper.ElectricWaterPriceMapper;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.BoardingHistoryRepository;
import com.springboot.architectural.repository.ElectricWaterPriceRepository;
import com.springboot.architectural.repository.RoomRegisRepository;
import com.springboot.architectural.service.BoardingHistoryService;
import com.springboot.architectural.service.ElectricWaterPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardingHistoryServiceImp implements BoardingHistoryService {
    @Autowired
    private BoardingHistoryRepository boardingHistoryRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoomRegisRepository roomRegisRepository;

    @Override
    public BoardingHistoryDto getById(int id) {
        Optional<BoardingHistory> boardingHistory = boardingHistoryRepository.findById(id);
        return boardingHistory.map(BoardingHistoryMapper.INSTANCE::boardingHistoryToBoardingHistoryDto).orElse(null);
    }

    @Override
    public List<BoardingHistoryDto> getAll() {
        List<BoardingHistory> boardingHistoryList = boardingHistoryRepository.findAll();
        return boardingHistoryList.stream().map(BoardingHistoryMapper.INSTANCE::boardingHistoryToBoardingHistoryDto).collect(Collectors.toList());
    }

    @Override
    public BoardingHistoryDto add(BoardingHistoryDto boardingHistoryDto) {
        BoardingHistory entity = BoardingHistoryMapper.INSTANCE.boardingHistoryDtoToBoardingHistory(boardingHistoryDto);
        Optional<RoomRegis> roomRegis = roomRegisRepository.findById(Integer.valueOf(boardingHistoryDto.getRoomRegisId()));
        Optional<Account> account = accountRepository.findById(boardingHistoryDto.getStudentId());
        if (roomRegis.isEmpty() || account.isEmpty()) return null;
        entity.setRoomRegis(roomRegis.get());
        entity.setAccount(account.get());
        return  BoardingHistoryMapper.INSTANCE.boardingHistoryToBoardingHistoryDto(boardingHistoryRepository.save(entity));
    }

    @Override
    public BoardingHistoryDto update(BoardingHistoryDto boardingHistoryDto) {
        if (boardingHistoryDto.getId() == null) return null;
        BoardingHistory entity = BoardingHistoryMapper.INSTANCE.boardingHistoryDtoToBoardingHistory(boardingHistoryDto);
        Optional<RoomRegis> roomRegis = roomRegisRepository.findById(Integer.valueOf(boardingHistoryDto.getRoomRegisId()));
        Optional<Account> account = accountRepository.findById(boardingHistoryDto.getStudentId());
        if (roomRegis.isEmpty() || account.isEmpty()) return null;
        entity.setRoomRegis(roomRegis.get());
        entity.setAccount(account.get());
        return  BoardingHistoryMapper.INSTANCE.boardingHistoryToBoardingHistoryDto(boardingHistoryRepository.save(entity));
    }

    @Override
    public boolean delete(Integer id) {
        Optional<BoardingHistory> r = boardingHistoryRepository.findById(id);
        if (r.isPresent())
        {
            boardingHistoryRepository.delete(r.get());
            return true;
        }
        return false;
    }


}
