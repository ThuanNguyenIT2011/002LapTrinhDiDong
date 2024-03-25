package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Regis")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Regis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date start_at;
    private Date end_at;
    private Date start_regis_at;
    @Column(name = "end_regis_at")
    private Date endRegisAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by", nullable = false)
    private Account account;
    private Date create_at;
    private Integer room_price_VND;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "RoomRegis",
//            joinColumns = @JoinColumn(name = "regis_id"),
//            inverseJoinColumns = @JoinColumn(name = "room_id"))
//    private List<Room> rooms;
    @OneToMany(mappedBy = "regis")
    private List<RoomRegis> roomRegis;
}
