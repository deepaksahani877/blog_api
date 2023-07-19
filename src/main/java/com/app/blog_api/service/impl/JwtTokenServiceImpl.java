package com.app.blog_api.service.impl;

import com.app.blog_api.entity.JwtToken;
import com.app.blog_api.repository.JwtTokenRepository;
import com.app.blog_api.service.JwtTokenService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;

    public JwtTokenServiceImpl(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    public void createOrUpdateToken(JwtToken token) {
        jwtTokenRepository.save(token);
    }

    @Override
    public void createOrUpdateAllTokens(List<JwtToken> tokens) {
        jwtTokenRepository.saveAll(tokens);
    }

    @Override
    public JwtToken getToken(String jwtToken) {
        return jwtTokenRepository.findByToken(jwtToken);
    }

    @Override
    public List<JwtToken> getTokensByUserId(Long userId) {
        return jwtTokenRepository.findAllByUserIdIs(userId);
    }


}
