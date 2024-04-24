package com.springboot.architectural.service;

import com.springboot.architectural.dto.InforDto;
import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

public interface InforService {
    InforDto updateInfor(String firstName, String lastName, String username, MultipartFile image);
}
