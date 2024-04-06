package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.entity.Regis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RegisMapper {
    RegisMapper INSTANCE = Mappers.getMapper(RegisMapper.class );
    @Mapping(target = "endRegisAt", source = "regis.endRegisAt")
    @Mapping(target = "endAt", source = "regis.endAt")
    @Mapping(target = "createBy", source = "regis.account.username")
    RegisDto regisToRegisDto(Regis regis);
    @Mapping(target = "endRegisAt", source = "regisDto.endRegisAt")
    @Mapping(target = "endAt", source = "regisDto.endAt")
    Regis regisDtoToRegis(RegisDto regisDto);
}
