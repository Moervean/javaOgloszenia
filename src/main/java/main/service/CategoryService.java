package main.service;

import main.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category c);
    void delete(Long id);
    Category findById(Long id);
    List<Category> findAll();
}
