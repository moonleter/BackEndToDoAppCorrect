package com.osu.BackEndToDoApp.dto;

import com.osu.BackEndToDoApp.model.Category;
import com.osu.BackEndToDoApp.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDto {
    private long id;
    @NotBlank(message = "Task name can not be blank")
    @Size(max = 100, message = "Task name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Task description cannot exceed 500 characters")
    private String description;

    
    private Boolean completed;
    private LocalDateTime created;

    //@EnumStringValid(enumClass = Priority.class, message = "Invalid priority value") //todo
    private Priority priority;
    private List<Category> category;


    public TaskDto() {
    }

    public TaskDto(long id, String name, String description, Boolean completed, LocalDateTime created, Priority priority, List<Category> category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.created = created;
        this.priority = priority;
        this.category = category;
    }

    public TaskDto(long id, String name, String description, Boolean completed, LocalDateTime created, Priority priority) { //přetížený konstruktor pro přidání tasku
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.created = created;
        this.priority = priority;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

}