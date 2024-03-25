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
    private String create_by;
    private Date create_at;
    private Boolean disable;
    private String img;
}
