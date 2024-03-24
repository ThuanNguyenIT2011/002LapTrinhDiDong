package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ElectricWaterPrice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectricWaterPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "room_regis_id")
    private RoomRegis roomRegis;
    private Integer month;
    private Integer electric_kg;
    private Integer water_m3;
    private Integer electric_unit_price;
    private Integer water_unit_price;

}
