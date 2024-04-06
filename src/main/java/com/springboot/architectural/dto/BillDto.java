package com.springboot.architectural.dto;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.BoardingHistory;
import com.springboot.architectural.entity.ElectricWaterPrice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Integer id;
    private String title;
    private String content;
    private Integer price;
    private Boolean pay;
    private Date createAt;
    private AccountDto accountDto;
    private String acceptBy;
    private BoardingHistoryDto boardingHistory;
    private String boardingId;
    private ElectricWaterPriceDto electricWaterPrice;
    private String electricWaterPriceId;

}
