package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.services.PostService;
import com.dauphine.blogger_box_backend.dto.UpdatePostRequest; 
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API")
public class PostController {

    private final PostService postService; // Renommé pour plus de clarté

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts", description = "Get all posts or filter by title/content")
    public List<Post> retrieveAllPosts(@RequestParam(required = false) String value) {
        // Logique de recherche demandée au slide 33
        return (value == null || value.isBlank())
                ? postService.getAll()
                : postService.getAllByTitleOrContent(value);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by ID")
    public Post retrievePostById(@PathVariable UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestBody CreationPostRequest request) {
        // Utilisation de ton DTO Record
        return postService.create(request.title(), request.content(), request.categoryId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest request) {
        return postService.update(id, request.title(), request.content());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post")
    public boolean deletePost(@PathVariable UUID id) {
        return postService.deleteById(id); // Retourne true/false comme vu précédemment
    }
}