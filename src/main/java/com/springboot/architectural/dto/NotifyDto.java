package com.springboot.architectural.dto;

import com.springboot.architectural.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyDto {
    private Integer id;
    private String title;
    private String content;
    private String created;
    private AccountDto account;
}
