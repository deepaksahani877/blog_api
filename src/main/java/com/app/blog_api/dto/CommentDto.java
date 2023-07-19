package com.app.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    @NotBlank(message = "post url required to comment")

    private String postUrl;
    @NotBlank(message = "User name required")

    private String userName;
    @NotBlank(message = "User email required")

    private String userEmail;
    @NotBlank(message = "comment required")

    private String comment;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
