package com.dauphine.blogger_box_backend.services.impl;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
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
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException(id)); // 
    }

    @Override
    public Category update(UUID id, String name) throws CategoryNotFoundByIdException {
        Category category = getById(id); // Va lever l'exception si non trouvé
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public Category create(String name) {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName(name);
        return repository.save(category); // Sauvegarde en base
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