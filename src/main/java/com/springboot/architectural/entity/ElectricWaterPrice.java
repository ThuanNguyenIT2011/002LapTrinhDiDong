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
    @Column(name = "electric_kg")
    private Integer electricKg;
    @Column(name = "water_m3")
    private Integer waterM3;
    @Column(name = "electric_unit_price")
    private Integer electricUnitPrice;
    @Column(name = "water_unit_price")
    private Integer waterUnitPrice;

}
