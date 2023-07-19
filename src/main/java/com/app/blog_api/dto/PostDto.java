package com.app.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @NotBlank(message ="Title required.")
    private String title;

    private String url;

    @NotBlank(message ="shortDescription required.")
    private String shortDescription;

    @NotBlank(message ="Post content required.")
    private String content;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}