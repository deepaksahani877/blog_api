package com.app.blog_api.service;

import com.app.blog_api.dto.CommentDto;

import java.util.HashMap;
import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getAllComments(Long id);

    HashMap<String,Object> deleteComment(Long id);

    CommentDto updateComment(CommentDto commentDto);
}
