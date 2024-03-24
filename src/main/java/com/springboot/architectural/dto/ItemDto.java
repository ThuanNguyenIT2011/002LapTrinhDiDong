package com.springboot.architectural.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Integer id;
    private String name;
    private int count;
    private String img;
    private Date created;
    private AccountDto account;
    private boolean disable;
}
