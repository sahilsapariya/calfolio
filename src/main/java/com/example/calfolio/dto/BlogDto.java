package com.example.calfolio.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String status;
    private MultipartFile photo;
}
