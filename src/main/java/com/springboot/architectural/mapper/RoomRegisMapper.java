package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.RegisDto;
import com.springboot.architectural.dto.RoomRegisDto;
import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.RoomRegis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RoomRegisMapper {
    RoomRegisMapper INSTANCE = Mappers.getMapper(RoomRegisMapper.class );
    @Mapping(target = "room_id", source = "roomRegis.room.id")
    @Mapping(target = "regis_id", source = "roomRegis.regis.id")
    RoomRegisDto roomRegisToRoomRegisDto(RoomRegis roomRegis);
    RoomRegis roomRegisDtoToRoomRegis(RoomRegisDto rooomRegisDto);
}
