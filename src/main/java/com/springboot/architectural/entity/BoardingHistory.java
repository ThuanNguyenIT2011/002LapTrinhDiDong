package com.springboot.architectural.entity;

import com.springboot.architectural.dto.RoomRegisDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BoardingHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "room_regis_id")
    private RoomRegis roomRegis;
    private Boolean disable;


}
