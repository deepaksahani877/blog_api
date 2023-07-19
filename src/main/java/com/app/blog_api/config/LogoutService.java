package com.app.blog_api.config;

import com.app.blog_api.entity.User;
import com.app.blog_api.exception.InvalidUsernameOrPasswordException;
import com.app.blog_api.security.jwt.JwtProvider;
import com.app.blog_api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class LogoutService implements LogoutHandler {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    public LogoutService(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String headerAuth = request.getHeader("Authorization");
        String jwtToken;
        final Map<String, Object> body = new HashMap<>();

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwtToken = headerAuth.substring(7);
            String email = jwtProvider.getUserNameFromJwtToken(jwtToken);
            User user = userService.getUserByEmail(email);
            jwtProvider.invalidateUser(user.getId());

            //Sending response
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            body.put("status", HttpServletResponse.SC_OK);
            body.put("message", "Successfully logged out.");
            final ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(response.getOutputStream(), body);
            } catch (IOException e) {
                throw new InvalidUsernameOrPasswordException();
            }

        } else {
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
                body.put("error", "Unauthorized");
                body.put("message", "User not logged in.");
                body.put("path", request.getServletPath());
                final ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
