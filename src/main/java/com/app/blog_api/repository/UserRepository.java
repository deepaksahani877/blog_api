package com.app.blog_api.repository;

import com.app.blog_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
