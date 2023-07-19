package com.app.blog_api.mapper;

import com.app.blog_api.dto.PostDto;
import com.app.blog_api.entity.Post;

public class PostMapper {
    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .shortDescription(postDto.getShortDescription())
                .content(postDto.getContent())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .shortDescription(post.getShortDescription())
                .content(post.getContent())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();
    }
}
