package com.app.blog_api.controller;


import com.app.blog_api.dto.PostDto;
import com.app.blog_api.service.PostService;
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
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    ResponseEntity<List<PostDto>> getPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        List<PostDto> posts = postService.getPosts(pageNo, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/admin/post/create-post")
    ResponseEntity<?> createPost(@RequestBody @Valid PostDto postDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errors", BindingResultUtils.getErrors(bindingResult)));
        }
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/admin/post/delete/{id}")
    ResponseEntity<HashMap<String, Object>> deletePost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }

    @PutMapping("/admin/post/update")
    ResponseEntity<?> updatePost(@RequestBody @Valid PostDto postDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errors", BindingResultUtils.getErrors(bindingResult)));
        }
        return new ResponseEntity<>(postService.updatePost(postDto), HttpStatus.OK);
    }

}