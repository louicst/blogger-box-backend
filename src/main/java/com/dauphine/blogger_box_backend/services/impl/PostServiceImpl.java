package com.dauphine.blogger_box_backend.services.impl;

import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.repositories.PostRepository;
import com.dauphine.blogger_box_backend.services.PostService;
import com.dauphine.blogger_box_backend.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CategoryService categoryService;

    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        if (category == null) return null;

        // Utilise le constructeur paramétré de ton modèle
        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            return repository.save(post);
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        // Tu pourras ajouter une méthode personnalisée dans PostRepository pour cela
        return repository.findAll().stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .toList();
    }
}