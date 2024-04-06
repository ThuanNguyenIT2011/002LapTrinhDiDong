package com.springboot.architectural.dto;

import com.springboot.architectural.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingHistoryDto {
    private Integer id;
    private String studentId;
    private AccountDto account;
    private RoomRegisDto roomRegis;
    private String roomRegisId;
    private Boolean disable;
}
