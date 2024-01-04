package com.app.blog_api.controller;


import com.app.blog_api.dto.*;
import com.app.blog_api.entity.JwtToken;
import com.app.blog_api.entity.User;
import com.app.blog_api.exception.InvalidUsernameOrPasswordException;
import com.app.blog_api.security.jwt.JwtProvider;
import com.app.blog_api.service.JwtTokenService;
import com.app.blog_api.service.UserService;
import com.app.blog_api.utils.BindingResultUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final JwtTokenService jwtTokenService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/signup")
    ResponseEntity<?> createUser(@RequestBody @Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errors", BindingResultUtils.getErrors(bindingResult)));
        }
        if (userService.existsUserByEmail(signupDto.getEmail())) {
            return new ResponseEntity<>(new ErrorResponse("Email already registered."
                    , "Email already registered.Please use another email and try again.", HttpStatus.CONFLICT.toString(), new Date()), HttpStatus.CONFLICT);
        }
        UserDto userDto = UserDto.builder()
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .build();
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errors", BindingResultUtils.getErrors(bindingResult)));
        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            JwtResponse response = JwtResponse.builder()
                    .jwt(jwtToken)
                    .email(userDetails.getUsername())
                    .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .build();

            User user = userService.getUserByEmail(loginDto.getEmail());
            JwtToken jwt = JwtToken.builder()
                    .token(jwtToken)
                    .userId(user.getId())
                    .isRevoked(false)
                    .isExpired(false)
                    .issueDate(LocalDateTime.now())
                    .build();

            jwtProvider.invalidateUser(user.getId()); // Invalidate previous tokens
            jwtTokenService.createOrUpdateToken(jwt); // Create new tokens
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new InvalidUsernameOrPasswordException();
        }
    }
}


