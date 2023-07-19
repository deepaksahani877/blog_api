package com.app.blog_api.config;

import com.app.blog_api.security.CustomUserDetailsService;
import com.app.blog_api.security.jwt.JwtAuthTokenFilter;
import com.app.blog_api.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtAuthTokenFilterBean {
    @Bean
    @Autowired
    public JwtAuthTokenFilter jwtAuthTokenFilter(JwtProvider provider, CustomUserDetailsService userDetailsService){
        return new JwtAuthTokenFilter(provider,userDetailsService);
    }

}
