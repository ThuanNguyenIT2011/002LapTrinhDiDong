package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.Regis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RegisMapper {
    RegisMapper INSTANCE = Mappers.getMapper(RegisMapper.class );
    @Mapping(target = "end_regis_at", source = "regis.endRegisAt")
    @Mapping(target = "end_at", source = "regis.end_at")
    @Mapping(target = "create_by", source = "regis.account.username")
    RegisDto regisToRegisDto(Regis regis);
    @Mapping(target = "endRegisAt", source = "regisDto.end_regis_at")
    @Mapping(target = "end_at", source = "regisDto.end_at")
    Regis regisDtoToRegis(RegisDto regisDto);
}
