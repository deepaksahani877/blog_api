package com.app.blog_api.service;

import com.app.blog_api.dto.PostDto;

import java.util.HashMap;
import java.util.List;

public interface PostService {
    List<PostDto> getPosts(int pageNo,int pageSize);
    PostDto createPost(PostDto post);
    HashMap<String,Object> deletePost(Long id);
    PostDto updatePost(PostDto postDto);
    PostDto getPostByUrl(String url);
}
