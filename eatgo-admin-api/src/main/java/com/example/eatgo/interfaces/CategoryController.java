package com.example.eatgo.interfaces;

import com.example.eatgo.application.CategoryService;
import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> list() {
        return categoryService.AllCategory();
    }

    @PostMapping("/categories")
    public ResponseEntity<?> addRegion(@RequestBody String name) throws URISyntaxException {
        Category category = categoryService.addCategory(name);

        URI uri = new URI("/regions/" + category.getId());
        return ResponseEntity.created(uri).body("{}");
    }

}
