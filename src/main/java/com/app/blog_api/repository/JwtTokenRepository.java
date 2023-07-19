package com.app.blog_api.repository;

import com.app.blog_api.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    JwtToken findByToken(String token);
    List<JwtToken> findAllByUserIdIs(Long userId);
}
