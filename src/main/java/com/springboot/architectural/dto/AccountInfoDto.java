package com.springboot.architectural.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountInfoDto {
    private String username;
    private String firstName;
    private String lastName;
    private boolean disable;
    private String role;
    private String token;
}
