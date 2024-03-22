package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Regis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Regis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date start_at;
    private Date end_at;
    private Date start_regis_at;
    private Date end_regis_at;
    @ManyToOne
    @JoinColumn(name = "create_by", nullable = false)
    private Account account;
    private Date create_at;
    private Integer room_price_VND;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "RoomRegis",
            joinColumns = @JoinColumn(name = "regis_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms;
}
