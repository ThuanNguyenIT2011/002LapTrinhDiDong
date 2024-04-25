package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.ItemDto;
import com.springboot.architectural.dto.RoomDtoRegis;
import com.springboot.architectural.entity.Room;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomDtoRegisMapper {
    public static RoomDtoRegis mappToRoomDtoRegis(Room room) {
        RoomDtoRegis roomDtoRegis = new RoomDtoRegis();

        roomDtoRegis.setId(room.getId());
        roomDtoRegis.setName(room.getName());
        roomDtoRegis.setSlot(room.getSlot());
        roomDtoRegis.setDisable(room.getDisable());
        roomDtoRegis.setImg(room.getImg());
        roomDtoRegis.setType(room.getType());

        List<ItemDto> itemDtos = new ArrayList<>();
        room.getRoomItems().forEach(roomItem -> {
            ItemDto itemDto = new ItemDto();
            itemDto = ItemMapper.mapToItemDto(roomItem.getItem(), itemDto);
            itemDtos.add(itemDto);
        });

        roomDtoRegis.setItems(itemDtos);

        return roomDtoRegis;
    }
}
