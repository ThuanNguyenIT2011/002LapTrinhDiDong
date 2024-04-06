package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.BillDto;
import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.entity.Bill;
import com.springboot.architectural.entity.BoardingHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BillMapper {
    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class );
    @Mapping(target = "acceptBy", source = "bill.account.username")
    @Mapping(target = "boardingId", source = "bill.boardingHistory.id")
    @Mapping(target = "electricWaterPriceId", source = "bill.electricWaterPrice.id")
    BillDto billToBillDto(Bill bill);
    Bill billDtoToBill(BillDto billDto);
}
