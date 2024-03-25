package com.springboot.architectural.dto;

import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisDto {
    @Id
    private Integer id;
    private Date start_at;
    private Date end_at;
    private Date start_regis_at;
    private Date end_regis_at;
    private String create_by;
    private Date create_at;
    private Integer room_price_VND;
    private AccountDto Account;
}
