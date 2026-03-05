package com.dauphine.blogger_box_backend.services.impl;

import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.services.CategoryService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service // Permet à Spring de créer le "Bean"
public class CategoryServiceImpl implements CategoryService {
    
    private final List<Category> temporaryCategories = new ArrayList<>();

    public CategoryServiceImpl() {
        // Initialisation comme sur ta capture
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @Override
    public List<Category> getAll() { return temporaryCategories; }

    @Override
    public Category getById(UUID id) {
        return temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        temporaryCategories.add(category);
        return category;
    }

    @Override
    public Category update(UUID id, String newName) {
        Category category = getById(id);
        if (category != null) { category.setName(newName); }
        return category;
    }

    @Override
    public void deleteById(UUID id) {
        temporaryCategories.removeIf(c -> c.getId().equals(id));
    }
}