package com.didier.carmanagementsystem.service.imp;

import com.didier.carmanagementsystem.model.Category;
import com.didier.carmanagementsystem.repository.CategoryRepository;
import com.didier.carmanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoryByStatus(String status) {
        return categoryRepository.findEmployeeByStatus(status);
    }

    @Override
    public void updateCategory(Category category) {

        if (category.getCategoryID() != null && categoryRepository.existsById(category.getCategoryID())){
            categoryRepository.save(category);
        }
    }


}
