package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.AccountDto;
import com.springboot.architectural.dto.ItemDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Item;

import java.util.Date;

public class ItemMapper {
    public static ItemDto mapToItemDto(Item item, ItemDto itemDto) {
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCount(item.getCount());
        itemDto.setImg(item.getImg());
        itemDto.setCreated(item.getCreateAt());

        AccountDto accountDto = Account.copyAccount(item.getAccount());

        itemDto.setAccount(accountDto);
        itemDto.setDisable(item.isDisable());

        return itemDto;
    }
}
