package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.BoardingHistoryDto;
import com.springboot.architectural.dto.ElectricWaterPriceDto;
import com.springboot.architectural.entity.BoardingHistory;
import com.springboot.architectural.entity.ElectricWaterPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ElectricWaterPriceMapper {
    ElectricWaterPriceMapper INSTANCE = Mappers.getMapper(ElectricWaterPriceMapper.class );
    @Mapping(target = "roomRegisId", source = "electricWaterPrice.roomRegis.id")
    ElectricWaterPriceDto electricWaterPriceToElectricWaterPriceDto(ElectricWaterPrice electricWaterPrice);
    ElectricWaterPrice electricWaterPriceDtoToElectricWaterPrice(ElectricWaterPriceDto electricWaterPriceDto);
}
