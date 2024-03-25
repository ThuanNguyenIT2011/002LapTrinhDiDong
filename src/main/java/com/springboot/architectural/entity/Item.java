package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private Account account;

    @Column(nullable = false)
    private boolean disable;

    @Column(nullable = false)
    private int count;

    @Column(length = 200)
    private String img;

}
