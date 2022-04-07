package com.example.eatgo.application;

import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.CategoryRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);

        List<Category> categories = new ArrayList<>();
        Category category = Category.builder().name("Korea food").build();
        categories.add(category);

        given(categoryRepository.findAll()).willReturn(categories);
    }

    @DisplayName("1. All Categories")
    @Test
    public void test1() {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.get(0);

        MatcherAssert.assertThat(category.getName(), Is.is("Korea food"));
    }
}