package com.dauphine.blogger_box_backend.services;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.models.Category;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id) throws CategoryNotFoundByIdException;
    Category create(String name);
    Category update(UUID id, String name) throws CategoryNotFoundByIdException;
    void deleteById(UUID id);
    List<Category> getAllLikeName(String name); 
}