package com.springboot.architectural.dto;

import com.springboot.architectural.service.RegisService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRegisDto {
    @Id
    private Integer id;
    private Integer room_id;
    private Integer regis_id;
    private RegisDto regis;
    private RoomDto room;
}
