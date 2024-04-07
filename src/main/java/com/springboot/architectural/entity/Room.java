package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer slot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by", nullable = false)
    private Account account;
    @Column(name = "create_at")
    private Date createAt;
    private Boolean disable;
    private String img;
    @OneToMany(mappedBy = "room")
    private List<RoomRegis> regis;

    @OneToMany(mappedBy = "room")
    private List<RoomItem> roomItems;

}
