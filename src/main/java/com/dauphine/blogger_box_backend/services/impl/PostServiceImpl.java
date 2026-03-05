package com.dauphine.blogger_box_backend.services.impl;

import com.dauphine.blogger_box_backend.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
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
    public Post getById(UUID id) throws PostNotFoundByIdException {
        // Utilisation de orElseThrow pour lever l'exception si l'ID n'existe pas 
        return repository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        // La méthode getById de categoryService lève déjà CategoryNotFoundByIdException [cite: 527]
        Category category = categoryService.getById(categoryId);

        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) throws PostNotFoundByIdException {
        Post post = getById(id); // Propagera PostNotFoundByIdException si non trouvé [cite: 527]
        post.setTitle(title);
        post.setContent(content);
        return repository.save(post);
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
        return repository.findAll().stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .toList();
    }

    @Override
    public List<Post> getAllByTitleOrContent(String value) {
        return repository.findAllByTitleOrContent(value);
    }
}