package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.CategoryDto;
import com.osu.BackEndToDoApp.exception.RecordNotFoundException;
import com.osu.BackEndToDoApp.model.Category;
import com.osu.BackEndToDoApp.model.Task;
import com.osu.BackEndToDoApp.repository.CategoryRepository;
import com.osu.BackEndToDoApp.repository.SubtaskRepository;
import com.osu.BackEndToDoApp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private TaskRepository taskRepository;
    private SubtaskRepository subtaskRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, TaskRepository taskRepository, SubtaskRepository subtaskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public Category createCategory(CategoryDto newCategoryDto) {
        Task task = taskRepository.findById(newCategoryDto.getTaskId()).orElseThrow(() -> new RecordNotFoundException("Task not found."));
        Category newCategory = new Category();
        newCategory.setName(newCategoryDto.getName());
        newCategory.setDescription(newCategoryDto.getDescription());
        newCategory.setTask(task);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Category not found."));
    }

    @Override
    public Category update(CategoryDto categoryDto) {
        Category fromDb = getCategoryById(categoryDto.getId());
        if (fromDb != null) {
            if (categoryDto.getName() != null) {
                fromDb.setName(categoryDto.getName());
            }
            if (categoryDto.getDescription() != null) {
                fromDb.setDescription(categoryDto.getDescription());
            }

            //Získání instance Task pomocí taskId z CategoryDto
            Task task = taskRepository.findById(categoryDto.getTaskId())
                    .orElseThrow(() -> new RecordNotFoundException("Task not found."));
            fromDb.setTask(task);
            return categoryRepository.save(fromDb);
        } else {
            throw new RecordNotFoundException("Category not found.");
        }
    }


    @Override
    public void deleteCategoryById(long id) {
        boolean exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Category not found.");
        }

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return categoryRepository.findAllCategoriesByNameContainsIgnoreCase(name);
    }
}
