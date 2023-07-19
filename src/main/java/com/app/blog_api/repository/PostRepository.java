package com.app.blog_api.repository;

import com.app.blog_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    Post findPostByUrl(String url);
    
}
