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

    private String title;

    @Column(name = "create_at",updatable = false)
    private Date createAt;

    @Column(nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @ManyToOne
    @JoinColumn(name = "create_by", nullable = false)
    private Account account;

}
