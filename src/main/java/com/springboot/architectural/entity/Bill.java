package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Integer price;
    private Boolean pay;

    @ManyToOne
    @JoinColumn(name = "accept_by")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "boarding_id")
    private BoardingHistory boardingHistory;

    @ManyToOne
    @JoinColumn(name = "electric_water_price_id")
    private ElectricWaterPrice electricWaterPrice;


}
