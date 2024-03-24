package com.springboot.architectural.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyRequest {
    private Integer id;
    private String title;
    private String content;
    private String username;
}
