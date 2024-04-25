package com.springboot.architectural.service;

import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.payload.Request.OrderRequest;

import java.util.Date;
import java.util.List;

public interface BoardingHistoryService {
    BoardingHistoryDto getById(int id);
    List<BoardingHistoryDto> getAll();
    BoardingHistoryDto add(BoardingHistoryDto boardingHistoryDto);
    BoardingHistoryDto update(BoardingHistoryDto boardingHistoryDto);
    boolean delete(Integer id);

    boolean checkUserIdRegistered(String username);

    boolean createRegister(OrderRequest orderRequest);

}
