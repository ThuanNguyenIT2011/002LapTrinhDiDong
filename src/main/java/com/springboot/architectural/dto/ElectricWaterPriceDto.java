package com.springboot.architectural.dto;

import com.springboot.architectural.entity.RoomRegis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectricWaterPriceDto {
    private Integer id;
    private RoomRegisDto roomRegis;
    private Integer roomRegisId;
    private Integer month;
    private Integer electricKg;
    private Integer waterM3;
    private Integer electricUnitPrice;
    private Integer waterUnitPrice;

}
