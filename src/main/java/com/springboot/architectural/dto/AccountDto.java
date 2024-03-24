package com.springboot.architectural.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String username;
    private String firstName;
    private String lastName;

    private boolean disable;
}
