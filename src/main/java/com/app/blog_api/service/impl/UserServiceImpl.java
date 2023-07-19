package com.app.blog_api.service.impl;

import com.app.blog_api.dto.UserDto;
import com.app.blog_api.entity.User;
import com.app.blog_api.mapper.UserMapper;
import com.app.blog_api.repository.UserRepository;
import com.app.blog_api.service.RoleService;
import com.app.blog_api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setRoles(Collections.singleton(roleService.getRoleByName("ROLE_ADMIN")));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }


}
