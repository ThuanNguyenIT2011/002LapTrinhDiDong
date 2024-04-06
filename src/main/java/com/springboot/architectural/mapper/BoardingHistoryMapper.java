package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.BoardingHistory;
import com.springboot.architectural.entity.Regis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BoardingHistoryMapper {
    BoardingHistoryMapper INSTANCE = Mappers.getMapper(BoardingHistoryMapper.class );
    @Mapping(target = "studentId", source = "boardingHistory.account.username")
    @Mapping(target = "roomRegisId", source = "boardingHistory.roomRegis.id")

    BoardingHistoryDto boardingHistoryToBoardingHistoryDto(BoardingHistory boardingHistory);

    BoardingHistory boardingHistoryDtoToBoardingHistory(BoardingHistoryDto boardingHistoryDto);
}
