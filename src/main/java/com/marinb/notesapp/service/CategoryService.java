package com.marinb.notesapp.service;

import com.marinb.notesapp.persistence.entity.Category;
import com.marinb.notesapp.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> searchCategory(Long id){
        return categoryRepository.findById(id);
    }
    public List<Category> listAllCategories(){
        return categoryRepository.findAll();
    }
}
