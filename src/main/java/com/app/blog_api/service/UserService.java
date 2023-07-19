package com.app.blog_api.service;


import com.app.blog_api.dto.UserDto;
import com.app.blog_api.entity.User;

public interface UserService {
    UserDto createUser(UserDto userDto);
    User getUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
