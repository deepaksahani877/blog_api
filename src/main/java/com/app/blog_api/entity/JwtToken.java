package com.app.blog_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jwt_token")
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "jwt_token")
    private String token;

    @Column(name = "is_revoked")
    private Boolean isRevoked;

    @Column(name = "is_expired")
    private Boolean isExpired;

    @Column(name = "token_issued_on")
    private LocalDateTime issueDate;

    @Column(name = "token_expired_on")
    private LocalDateTime expireDate;

}
