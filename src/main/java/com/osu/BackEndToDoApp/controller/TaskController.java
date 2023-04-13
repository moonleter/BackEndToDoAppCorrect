package com.osu.BackEndToDoApp.controller;

import com.osu.BackEndToDoApp.dto.TaskDto;
import com.osu.BackEndToDoApp.model.Task;
import com.osu.BackEndToDoApp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public Task create(@Valid @RequestBody TaskDto newTask) {
        return taskService.createTask(newTask);
    }

    @PutMapping("tasks/update")
    public Task update(@Valid @RequestBody TaskDto task) {
        return taskService.update(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}") //endopoint
    public Task get(@PathVariable("id") long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasks/search/priority/{priority}")
    public List<Task> searchByPriority(@PathVariable("priority") String priority) {
        return taskService.findTasksByPriority(priority);
    }

    @GetMapping("/tasks/search/{name}")
    public List<Task> searchByName(@PathVariable("name") String name) {
        return taskService.getTaskByName(name);
    }

    @GetMapping("/tasks/sorted/priority")
    public List<Task> getAllTasksSortedByPriority(@RequestParam("ascending") boolean ascending) {
        return taskService.getAllTasksSortedByPriority(ascending);
    }

    @GetMapping("/tasks/sorted/category")
    public List<Task> getAllTasksSortedByCategory(@RequestParam("ascending") boolean ascending) {
        return taskService.getAllTasksSortedByCategory(ascending);
    }

    @GetMapping("/tasks/sorted/name")
    public List<Task> getAllTasksSortedByName(@RequestParam("ascending") boolean ascending) {
        return taskService.getAllTasksSortedByName(ascending);
    }  //na vyzkoušení v PostManovi: http://localhost:8080/tasks/sorted/name?ascending=false


    @GetMapping("/tasks/search/combined/{name}") //vyhledávání naráz podle názvu a kategorie
    public List<Task> searchTasksAndSubtasksandCategoryByName(@PathVariable("name") String name) {
        return taskService.searchTasksAndSubtaskandCategorysByName(name);
    }
    @DeleteMapping("/tasks/delete/{id}")
    public void deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
    }


}
