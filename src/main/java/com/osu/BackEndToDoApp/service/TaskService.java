package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.TaskDto;
import com.osu.BackEndToDoApp.model.Task;
import jakarta.validation.Valid;

import java.util.List;

public interface TaskService {
    Task createTask(@Valid TaskDto newTask); //vytvoření nového tasku

    Task getTaskById(long id);//vytahnuti tasku podle ID

    List<Task> findTasksByPriority(String priority); //vytahnuti tasku podle priority

    Task update(@Valid TaskDto task); //aktualizace tasku

    void deleteTaskById(long id); //smazání tasku podle ID

    List<Task> getAllTasks(); //vrátí všechny tasky

    List<Task> getTaskByName(String name); //vrátí task podle jména

    List<Task> getAllTasksSortedByPriority(boolean ascending);
    List<Task> getAllTasksSortedByCategory(boolean ascending);
    List<Task> getAllTasksSortedByName(boolean ascending);
    List<Task> searchTasksAndSubtaskandCategorysByName(String name);


}
