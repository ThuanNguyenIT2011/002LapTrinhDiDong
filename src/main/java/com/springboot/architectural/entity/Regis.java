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
    @Column(name = "start_at")
    private Date startAt;
    @Column(name = "end_at")
    private Date endAt;
    @Column(name = "start_regis_at")
    private Date startRegisAt;
    @Column(name = "end_regis_at")
    private Date endRegisAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by", nullable = false)
    private Account account;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "room_price_VND")
    private Integer roomPriceVND;
    @OneToMany(mappedBy = "regis")
    private List<RoomRegis> roomRegis;
}
