package com.marinb.notesapp.controller;

import com.marinb.notesapp.persistence.entity.Category;
import com.marinb.notesapp.persistence.entity.Note;
import com.marinb.notesapp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity< List<Category>> listAllCategories(){
        List<Category> categories = categoryService.listAllCategories();
        if (categories.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


}
