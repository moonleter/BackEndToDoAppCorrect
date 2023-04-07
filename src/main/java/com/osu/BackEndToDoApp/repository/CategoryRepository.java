package com.osu.BackEndToDoApp.repository;

import com.osu.BackEndToDoApp.model.Category;
import com.osu.BackEndToDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllCategoriesByNameContainsIgnoreCase(String name);
}
