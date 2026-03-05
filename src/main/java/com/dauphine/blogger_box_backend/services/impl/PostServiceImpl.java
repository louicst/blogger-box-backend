package com.dauphine.blogger_box_backend.services.impl;

import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    private final List<Post> posts = new ArrayList<>();

    @Override
    public List<Post> getAll() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .toList();
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return posts.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .toList();
    }

    @Override
    public Post getById(UUID id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, categoryId, LocalDateTime.now());
        posts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}