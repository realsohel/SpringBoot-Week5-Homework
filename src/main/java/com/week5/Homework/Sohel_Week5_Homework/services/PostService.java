package com.week5.Homework.Sohel_Week5_Homework.services;


import com.week5.Homework.Sohel_Week5_Homework.dto.PostDto;

import java.util.List;

public interface PostService {

    public List<PostDto> getAllPosts();
    public PostDto createPost(PostDto post);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto inpPost, Long postId);
}
