package com.springboot.architectural.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    @Id
    private Integer id;
    private String name;
    private Integer slot;
    private String createBy;
    private Date createAt;
    private Boolean disable;
    private String img;
}
