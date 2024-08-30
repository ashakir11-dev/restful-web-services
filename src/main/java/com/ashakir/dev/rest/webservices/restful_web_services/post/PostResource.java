package com.ashakir.dev.rest.webservices.restful_web_services.post;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostResource {
    PostRepositery postRepositery;

    public PostResource(PostRepositery postRepositery) {
        this.postRepositery = postRepositery;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postRepositery.findAll();
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody Post post){
        postRepositery.save(post);
    }
}
