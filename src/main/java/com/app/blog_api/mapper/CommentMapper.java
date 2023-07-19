package com.app.blog_api.mapper;

import com.app.blog_api.dto.CommentDto;
import com.app.blog_api.entity.Comment;

public class CommentMapper {
    public static Comment mapToComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getUserName())
                .email(commentDto.getUserEmail())
                .comment(commentDto.getComment())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }

    public static CommentDto mapToCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .userName(comment.getName())
                .userEmail(comment.getEmail())
                .postUrl(comment.getPost().getUrl())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .comment(comment.getComment())
                .build();

    }
}
