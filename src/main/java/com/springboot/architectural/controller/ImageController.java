package com.springboot.architectural.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/images")
public class ImageController {
    // Đường dẫn tới thư mục chứa hình ảnh
    private static final String IMAGE_FOLDER = "src/main/resources/static/";

    @GetMapping("/get")
    public ResponseEntity<Resource> getImage(@RequestParam("image") String imageName) {
        // Đọc hình ảnh từ thư mục
        Path imagePath = Paths.get(IMAGE_FOLDER + imageName);
        Resource resource = null;
        try {
            resource = new org.springframework.core.io.UrlResource(imagePath.toUri());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Hoặc MediaType.IMAGE_PNG nếu hình ảnh là PNG
                .body(resource);
        // Trả về hình ảnh dưới dạng ResponseEntity

    }
}
