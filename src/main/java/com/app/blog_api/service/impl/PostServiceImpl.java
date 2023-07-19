package com.app.blog_api.service.impl;

import com.app.blog_api.dto.PostDto;
import com.app.blog_api.entity.Post;
import com.app.blog_api.exception.InvalidResourceIdException;
import com.app.blog_api.exception.ResourceNotFoundException;
import com.app.blog_api.mapper.PostMapper;
import com.app.blog_api.repository.PostRepository;
import com.app.blog_api.service.PostService;
import com.app.blog_api.utils.UrlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getPosts(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Post> page = postRepository.findAll(pageable);
        return page.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        postDto.setUrl(UrlUtils.getUrl(postDto.getTitle()));
        Post post = postRepository.save(PostMapper.mapToPost(postDto));
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public HashMap<String, Object> deletePost(Long id) {
        if(postRepository.existsById(id)){
            HashMap<String, Object> responseStatus = new HashMap<>();
            responseStatus.put("success", true);
            responseStatus.put("message", "Post Successfully deleted.");
            postRepository.deleteById(id);
            return responseStatus;
        }else{
            throw new ResourceNotFoundException("post","id", String.valueOf(id));
        }
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        if(postDto.getId()!=null){
            Optional<Post> existingPostOptional = postRepository.findById(postDto.getId());
            if(existingPostOptional.isPresent()){
                postDto.setUrl(UrlUtils.getUrl(postDto.getTitle()));
                Post post = postRepository.save(PostMapper.mapToPost(postDto));
                return PostMapper.mapToPostDto(post);
            }else {
                throw new ResourceNotFoundException("post","id", String.valueOf(postDto.getId()));
            }
        }
        else{
            throw new InvalidResourceIdException("Post id required for update",null);
        }
    }

    @Override
    public PostDto getPostByUrl(String url) {
        return PostMapper.mapToPostDto(postRepository.findPostByUrl(url));
    }


}
