package com.springboot.architectural.service;

import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.exception.NotifyNotfoundException;
import com.springboot.architectural.payload.Request.NotifyRequest;
import org.hibernate.sql.Update;

import java.util.List;

public interface NotifyService {
    void saveNotify(NotifyRequest notifyRequest);
    boolean deleteNotify(Integer id) throws NotifyNotfoundException;

    List<NotifyDto> getAllNotify(String searchContent, String disable, String typeSort);

    NotifyDto getNotifyById(Integer id) throws NotifyNotfoundException;

    boolean updateNotify(NotifyRequest notifyRequest) throws NotifyNotfoundException;

    List<NotifyDto> getNotifyByUsername(String username);
}
