package com.example.eatgo.application;

import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.CategoryRepository;
import com.example.eatgo.domain.Region;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> AllCategory() {
        return categoryRepository.findAll();
    }

    public Category addCategory(String name) {
     Category category = Category.builder().name(name).build();
        categoryRepository.save(category);
        return category;
    }
}
