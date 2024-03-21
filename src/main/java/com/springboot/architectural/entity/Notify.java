package com.springboot.architectural.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Notify")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date createAt;

    @Column(nullable = false, length = 20)
    private String createBy;

    @Column(nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
