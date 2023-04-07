package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.CategoryDto;
import com.osu.BackEndToDoApp.model.Category;
import jakarta.validation.Valid;

import java.util.List;


public interface CategoryService {
    Category createCategory(@Valid CategoryDto newCategory); //vytvoření nové kategorie

    Category getCategoryById(long id);//vytahnuti kateogrie podle ID

    Category update(@Valid CategoryDto category); //aktualizace kategorie

    void deleteCategoryById(long id); //smazání kategorie podle ID

    List<Category> getAllCategories(); //vrátí všechny kategorie

    List<Category> getCategoryByName(String name); //vrátí kategorii podle jména
}
