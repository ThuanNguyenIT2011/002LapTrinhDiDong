package com.springboot.architectural.mapper;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Notify;
import com.springboot.architectural.payload.Request.NotifyRequest;

public class NotifyRequestMapper {
    public static Notify mappToNotify(NotifyRequest notifyRequest, Notify notify){
        notify.setId(notifyRequest.getId());
        notify.setContent(notifyRequest.getContent());
        notify.setTitle(notifyRequest.getTitle());
        Account account = new Account();
        account.setUsername(notifyRequest.getUsername());

        notify.setAccount(account);
        notify.setId(notifyRequest.getId());
        return notify;
    }
}
