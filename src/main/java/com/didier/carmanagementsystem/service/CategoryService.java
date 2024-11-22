package com.didier.carmanagementsystem.service;

import com.didier.carmanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);
    List<Category> getAllCategories();

    List<Category> getCategoryByStatus(String status);

    void updateCategory(Category category);




}
