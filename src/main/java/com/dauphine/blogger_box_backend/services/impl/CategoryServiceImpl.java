package com.dauphine.blogger_box_backend.services.impl;

import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.repositories.CategoryRepository;
import com.dauphine.blogger_box_backend.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository; //

    // Injection du repository via le constructeur
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll(); // Récupère tout depuis Supabase
    }

    @Override
    public Category getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName(name);
        return repository.save(category); // Sauvegarde en base
    }

    @Override
    public Category update(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
            return repository.save(category);
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Category> getAllLikeName(String name) {
        return repository.findAllLikeName(name);
    }
}