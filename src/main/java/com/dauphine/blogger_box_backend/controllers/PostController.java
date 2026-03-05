package com.dauphine.blogger_box_backend.controllers;
import com.dauphine.blogger_box_backend.services.PostService; // Pour trouver l'interface
import com.dauphine.blogger_box_backend.dto.UpdatePostRequest; 
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> retrieveAllPosts() {
        return service.getAll();
    }

    @PostMapping
    public Post createPost(@RequestBody CreationPostRequest request) {
        return service.create(request.title(), request.content(), request.categoryId());
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest request) {
        return service.update(id, request.title(), request.content());
    }
}