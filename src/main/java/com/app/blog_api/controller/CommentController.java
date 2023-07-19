package com.app.blog_api.controller;

import com.app.blog_api.dto.CommentDto;
import com.app.blog_api.service.CommentService;
import com.app.blog_api.utils.BindingResultUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    ResponseEntity<List<CommentDto>> getComments(@RequestParam("postId") Long postId) {
        return new ResponseEntity<>(commentService.getAllComments(postId),HttpStatus.OK);
    }

    @PostMapping("/comment/new-comment")
    ResponseEntity<?> createComment(@RequestBody @Valid CommentDto commentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errors",BindingResultUtils.getErrors(bindingResult)));
        }
        return new ResponseEntity<>(commentService.createComment(commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/comment/delete/{id}")
    ResponseEntity<HashMap<String, Object>> deleteComment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(commentService.deleteComment(id), HttpStatus.OK);
    }


    @PutMapping("/comment/update")
    ResponseEntity<?> updateComment(@RequestBody @Valid CommentDto commentDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errors",BindingResultUtils.getErrors(bindingResult)));
        }
        CommentDto commentResponse = commentService.updateComment(commentDto);
        return new ResponseEntity<>(commentResponse,HttpStatus.OK);
    }

}
