package com.springboot.architectural.mapper;

import com.springboot.architectural.entity.Room;
import com.springboot.architectural.payload.Request.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class );
    RoomDto roomToRoomDto(Room room);
    Room roomDtotoRoom(RoomDto roomDto);
}
