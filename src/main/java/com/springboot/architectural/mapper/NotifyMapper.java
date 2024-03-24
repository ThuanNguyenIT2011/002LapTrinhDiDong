package com.springboot.architectural.mapper;

import com.springboot.architectural.dto.AccountDto;
import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Notify;

public class NotifyMapper {
    public static NotifyDto mapToNotifyDto(Notify notify, NotifyDto notifyDto) {
        notifyDto.setId(notify.getId());
        notifyDto.setTitle(notify.getTitle());
        notifyDto.setContent(notify.getContent());
        AccountDto account = Account.copyAccount(notify.getAccount());
        notifyDto.setAccount(account);
        notifyDto.setCreated(notify.getCreateAt());
        return notifyDto;
    }

    public static Notify mapToNotify(NotifyDto notifyDto, Notify notify) {
        notify.setContent(notifyDto.getContent());
        notify.setId(notify.getId());
        notify.setTitle(notifyDto.getTitle());
        Account account = new Account();
        account.setUsername(notifyDto.getAccount().getUsername());

        notify.setAccount(account);
        return notify;
    }
}
