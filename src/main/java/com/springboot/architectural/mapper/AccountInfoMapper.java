package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.AccountInfoDto;
import com.springboot.architectural.entity.Account;

public class AccountInfoMapper {
    public static AccountInfoDto mapToAccountInfo(Account account) {
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setUsername(account.getUsername());
        accountInfoDto.setLastName(account.getLastName());
        accountInfoDto.setFirstName(account.getFirstName());
        if (! account.getRoles().isEmpty()) {
            accountInfoDto.setRole(account.getRoles().stream().findFirst().get().getName());
        } else {
            accountInfoDto.setRole("");
        }

        return accountInfoDto;
    }
}
