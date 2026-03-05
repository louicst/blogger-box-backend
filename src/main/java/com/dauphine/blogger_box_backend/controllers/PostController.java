package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger_box_backend.services.PostService;
import com.dauphine.blogger_box_backend.dto.UpdatePostRequest; 
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.dto.CreationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/v1/posts")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Post API")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts", description = "Get all posts or filter by title/content")
    public ResponseEntity<List<Post>> retrieveAllPosts(@RequestParam(required = false) String value) {
        List<Post> posts = (value == null || value.isBlank())
                ? postService.getAll()
                : postService.getAllByTitleOrContent(value);
        return ResponseEntity.ok(posts); // Retourne 200 OK [cite: 471]
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by ID")
    public ResponseEntity<Post> retrievePostById(@PathVariable UUID id) throws PostNotFoundByIdException {
        // L'exception est propagée au GlobalDefaultExceptionHandler [cite: 553, 558]
        return ResponseEntity.ok(postService.getById(id)); // [cite: 555]
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public ResponseEntity<Post> createPost(@RequestBody CreationPostRequest request) throws CategoryNotFoundByIdException {
        Post post = postService.create(request.title(), request.content(), request.categoryId());
        // Retourne 201 Created avec l'URI de la ressource [cite: 497, 498]
        return ResponseEntity
                .created(URI.create("v1/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest request) throws PostNotFoundByIdException {
        return ResponseEntity.ok(postService.update(id, request.title(), request.content()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retourne 204 No Content pour une suppression 
    }
}