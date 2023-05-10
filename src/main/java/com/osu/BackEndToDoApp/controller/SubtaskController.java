package com.osu.BackEndToDoApp.controller;

import com.osu.BackEndToDoApp.dto.SubtaskDto;
import com.osu.BackEndToDoApp.model.Subtask;
import com.osu.BackEndToDoApp.service.SubtaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @PostMapping("/subtasks")
    public Subtask create(@Valid @RequestBody SubtaskDto newSubtask) {
        return subtaskService.createSubTask(newSubtask);
    }

    @PutMapping("/subtasks")
    public Subtask update(@Valid @RequestBody SubtaskDto subtask) {
        return subtaskService.update(subtask);
    }

    @GetMapping("/subtasks")
    public List<Subtask> getAll() {
        return subtaskService.getAllSubTasks();
    }

    @GetMapping("/subtasks/{id}") //endopoint
    public Subtask get(@PathVariable("id") long id) {
        return subtaskService.getSubTaskById(id);
    }

    @GetMapping("/subtasks/search/{name}")
    public List<Subtask> searchByName(@PathVariable("name") String name) {
        return subtaskService.getSubTaskByName(name);
    }
    @DeleteMapping("/subtasks/delete/{id}")
    public void deleteTaskById(@PathVariable("id") Long id) {
        subtaskService.deleteSubTaskById(id);

    }
}


