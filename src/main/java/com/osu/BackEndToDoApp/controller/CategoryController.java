package com.osu.BackEndToDoApp.controller;

import com.osu.BackEndToDoApp.dto.CategoryDto;
import com.osu.BackEndToDoApp.model.Category;
import com.osu.BackEndToDoApp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public Category create(@Valid @RequestBody CategoryDto newCategory) {
        return categoryService.createCategory(newCategory);
    }

    @PutMapping("categories/update")
    public Category update(@Valid @RequestBody CategoryDto category) {
        return categoryService.update(category);
    }

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}") //endopoint PostMan: http://localhost:8080/categories/1
    public Category get(@PathVariable("id") long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/categories/search/{name}")
    public List<Category> searchByName(@PathVariable("name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @DeleteMapping("/categories/delete/{id}")
    public void deleteTaskById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);

    }
}
