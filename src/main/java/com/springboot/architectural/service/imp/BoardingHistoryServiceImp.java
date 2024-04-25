package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.entity.*;
import com.springboot.architectural.mapper.BoardingHistoryMapper;
import com.springboot.architectural.mapper.ElectricWaterPriceMapper;
import com.springboot.architectural.payload.Request.OrderRequest;
import com.springboot.architectural.repository.*;
import com.springboot.architectural.service.BoardingHistoryService;
import com.springboot.architectural.service.ElectricWaterPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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

    @Autowired
    private RegisRepository regisRepository;

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

    @Override
    public boolean checkUserIdRegistered(String username) {
        LocalDate localDate = LocalDate.now();
        List<Regis> regisList = regisRepository.findEntitiesByDateRange(localDate);
        if (regisList.size() > 0) {
            Regis regis = regisList.get(regisList.size() - 1);
            List<BoardingHistory> boardingHistoryList = boardingHistoryRepository.findEntitiesByDateRange(regis.getStartRegisAt(),
                    regis.getEndRegisAt(), new Account(username));

            return  boardingHistoryList.size() > 0;
        }


        return true;
    }

    @Override
    public boolean createRegister(OrderRequest orderRequest) {
        LocalDate localDate = LocalDate.now();
        List<Regis> regisList = regisRepository.findEntitiesByDateRange(localDate);
        if (regisList.size() > 0) {
            Regis regis = regisList.get(0);
            for (RoomRegis roomRegis : regis.getRoomRegis()) {
                if (roomRegis.getRoom().getId() == orderRequest.getIdRoom()) {
                    BoardingHistory boardingHistory = new BoardingHistory();
                    boardingHistory.setCreated(new Date());
                    boardingHistory.setAccount(new Account(orderRequest.getUsername()));
                    boardingHistory.setDisable(false);
                    boardingHistory.setRoomRegis(roomRegis);

                    boardingHistoryRepository.save(boardingHistory);

                    return true;
                }
            }
        }


        return false;
    }


}
