package com.week5.Homework.Sohel_Week5_Homework.services;


import com.week5.Homework.Sohel_Week5_Homework.dto.PostDto;
import com.week5.Homework.Sohel_Week5_Homework.entities.PostEntity;
import com.week5.Homework.Sohel_Week5_Homework.entities.UserEntity;
import com.week5.Homework.Sohel_Week5_Homework.exceptions.ResourceNotFoundException;
import com.week5.Homework.Sohel_Week5_Homework.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImp implements PostService{

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto post) {
        PostEntity postEntity = modelMapper.map(post,PostEntity.class);
        postRepository.save(postEntity);
        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}", user);
        PostEntity postEntity = postRepository
                .findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Post not found with Id" + id)
                );
        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto inpPost, Long postId) {
        PostEntity olderPost = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found with Id" + postId)
        );
        inpPost.setId(postId);
        modelMapper.map(inpPost,olderPost);

        return modelMapper.map(postRepository.save(olderPost),PostDto.class);
    }
}
