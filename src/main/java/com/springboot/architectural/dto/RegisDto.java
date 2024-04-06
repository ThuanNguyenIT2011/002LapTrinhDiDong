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
    private Date startAt;
    private Date endAt;
    private Date startRegisAt;
    private Date endRegisAt;
    private String createBy;
    private Date createAt;
    private Integer roomPriceVND;
    private AccountDto Account;
}
