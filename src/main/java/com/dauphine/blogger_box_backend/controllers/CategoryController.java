package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    // On l'appelle 'categoryService' pour être cohérent
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve all categories or filter by name")
    public List<Category> getAll(@RequestParam(required = false) String name) {
        // Le nom de la variable doit correspondre à celui déclaré en haut !
        return (name == null || name.isBlank())
                ? categoryService.getAll()
                : categoryService.getAllLikeName(name);
    }

    @GetMapping("/{id}")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody String name) {
        return categoryService.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable UUID id, @RequestBody String name) {
        return categoryService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteById(id);
    }
}