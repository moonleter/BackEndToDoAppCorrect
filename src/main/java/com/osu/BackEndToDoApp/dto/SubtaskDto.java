package com.osu.BackEndToDoApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SubtaskDto {
    private long id;

    @NotBlank(message = "Subtask name can not be blank")
    @Size(max = 100, message = "Subtask name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Task description cannot exceed 500 characters")
    private String description;
    private Boolean completed;
    private long taskId;

    public SubtaskDto() {
    }

    public SubtaskDto(long id, String name, String description, Boolean completed, long taskId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.taskId = taskId;
        completed = false;
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

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
