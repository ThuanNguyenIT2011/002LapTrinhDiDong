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

    @ManyToOne
    @JoinColumn(name = "create_by", nullable = false)
    private Account account;

    private Date create_at;
    private Integer disable;
    private String img;
    @ManyToMany(mappedBy = "rooms")
    private List<Regis> regises;

}
