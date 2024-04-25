package com.springboot.architectural.dto;

import com.springboot.architectural.entity.RoomItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoRegis {
    private Integer id;
    private String name;
    private Integer slot;
    private Boolean disable;
    private String img;
    private String type;
    private int count;
    private double price;
//    private double priceElectric;
//    private double priceWater;


    private List<ItemDto> items = new ArrayList<>();
}
