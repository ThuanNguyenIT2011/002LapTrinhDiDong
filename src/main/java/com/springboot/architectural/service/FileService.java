package com.springboot.architectural.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public boolean save(MultipartFile multipartFile);

    public Resource loadFile(String fileName);

    public void init();
}
