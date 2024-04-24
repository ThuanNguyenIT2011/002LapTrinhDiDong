package com.springboot.architectural.service;

import com.springboot.architectural.dto.InforDto;

public interface InforService {
    InforDto updateInfor(String firstName, String lastName, String username);
}
