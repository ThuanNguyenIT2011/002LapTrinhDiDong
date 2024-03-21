package com.springboot.architectural.service.imp;

import com.springboot.architectural.repository.NotifyRepository;
import com.springboot.architectural.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImp implements NotifyService {
    @Autowired
    private NotifyRepository notifyRepository;
}
