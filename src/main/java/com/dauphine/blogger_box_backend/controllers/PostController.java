package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Post API")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private final List<Post> posts = new ArrayList<>();

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> getAll() {
        // Tri décroissant pour avoir les plus récents en premier (Page 70)
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .toList();
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post create(@RequestBody CreationPostRequest request) {
        Post post = new Post(
            UUID.randomUUID(), 
            request.title(), 
            request.content(), 
            request.categoryId(), 
            LocalDateTime.now()
        );
        posts.add(post);
        return post;
    }
}