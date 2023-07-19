package com.app.blog_api.service.impl;

import com.app.blog_api.dto.CommentDto;
import com.app.blog_api.entity.Comment;
import com.app.blog_api.exception.InvalidResourceIdException;
import com.app.blog_api.exception.ResourceNotFoundException;
import com.app.blog_api.mapper.CommentMapper;
import com.app.blog_api.mapper.PostMapper;
import com.app.blog_api.repository.CommentRepository;
import com.app.blog_api.service.CommentService;
import com.app.blog_api.service.PostService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PostService postService,CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(PostMapper.mapToPost(postService.getPostByUrl(commentDto.getPostUrl())));
        Comment res = commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(res);
    }

    @Override
    public List<CommentDto> getAllComments(Long id) {
        return commentRepository.findAllByPostId(id).stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public HashMap<String, Object> deleteComment(Long id) {
        if(commentRepository.existsById(id)){
            HashMap<String, Object> responseStatus = new HashMap<>();
            responseStatus.put("success", true);
            responseStatus.put("message", "Comment Successfully deleted.");
            commentRepository.deleteById(id);
            return responseStatus;
        }else{
            throw new ResourceNotFoundException("comment","id", String.valueOf(id));
        }
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        if(commentDto.getId()!=null){
            Optional<Comment> existingPostOptional = commentRepository.findById(commentDto.getId());
            if(existingPostOptional.isPresent()){
                Comment comment = CommentMapper.mapToComment(commentDto);
                comment.setPost(PostMapper.mapToPost(postService.getPostByUrl(commentDto.getPostUrl())));
                Comment commentResponse = commentRepository.save(comment);
                commentResponse.setPost(PostMapper.mapToPost(postService.getPostByUrl(commentDto.getPostUrl())));
                return CommentMapper.mapToCommentDto(commentResponse);
            }else {
                throw new ResourceNotFoundException("Comment","id", String.valueOf(commentDto.getId()));
            }
        }
        else{
            throw new InvalidResourceIdException("Comment id required for update",null);
        }
    }
}
