package com.week5.Homework.Sohel_Week5_Homework.controllers;


import com.week5.Homework.Sohel_Week5_Homework.dto.PostDto;
import com.week5.Homework.Sohel_Week5_Homework.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable(name = "postId")  Long id){
        return postService.getPostById(id);
    }
    @PostMapping
    public PostDto createPost(@RequestBody PostDto post){

        return postService.createPost(post);
    }
    
    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto inpPost, @PathVariable Long postId){
        return postService.updatePost(inpPost,postId);
    }
}
