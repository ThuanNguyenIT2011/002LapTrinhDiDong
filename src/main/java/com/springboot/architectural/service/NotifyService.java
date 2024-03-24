package com.springboot.architectural.service;

import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.exception.NotifyNotfoundException;
import com.springboot.architectural.payload.Request.NotifyRequest;

import java.util.List;

public interface NotifyService {
    void saveNotify(NotifyRequest notifyRequest);
    boolean deleteNotify(Integer id) throws NotifyNotfoundException;

    List<NotifyDto> getAllNotify();

    NotifyDto getNotifyById(Integer id) throws NotifyNotfoundException;

    List<NotifyDto> getNotifyByUsername(String username);
}
