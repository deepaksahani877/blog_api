package com.app.blog_api.service;

import com.app.blog_api.entity.JwtToken;


import java.util.List;

public interface JwtTokenService {
    void createOrUpdateToken(JwtToken token);
    void createOrUpdateAllTokens(List<JwtToken> tokens);
    JwtToken getToken(String jwtToken);
    List<JwtToken> getTokensByUserId(Long userId);
}
