package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.services.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories") // Tip #2: Versioning
public class CategoryController {

    private final CategoryService service;

    // Injection par constructeur comme sur ton slide
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> retrieveAllCategories() {
        return service.getAll(); // On délègue au service
    }

    @GetMapping("/{id}")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody String name) {
        return service.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable UUID id, @RequestBody String name) {
        return service.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        service.deleteById(id);
    }
}