package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.SubtaskDto;
import com.osu.BackEndToDoApp.exception.RecordNotFoundException;
import com.osu.BackEndToDoApp.model.Subtask;
import com.osu.BackEndToDoApp.model.Task;
import com.osu.BackEndToDoApp.repository.SubtaskRepository;
import com.osu.BackEndToDoApp.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskServiceImpl implements SubtaskService {
    private TaskRepository taskRepository;
    private SubtaskRepository subtaskRepository;

    public SubtaskServiceImpl(TaskRepository taskRepository, SubtaskRepository subtaskRepository) {
        this.taskRepository = taskRepository;
        this.subtaskRepository = subtaskRepository;
    }


    @Override
    public Subtask createSubTask(@Valid SubtaskDto newSubTaskDto) { //todo orpavit, aby když nezádám compelted tka šel vytvořit subtask i tak
        Task task = taskRepository.findById(newSubTaskDto.getTaskId()).orElseThrow(() -> new RecordNotFoundException("Task not found."));
        Subtask newSubTask = new Subtask(
                newSubTaskDto.getId(),
                newSubTaskDto.getName(),
                newSubTaskDto.isCompleted(),
                newSubTaskDto.getDescription(),
                task
        );

        return subtaskRepository.save(newSubTask);
    }


    @Override
    public Subtask getSubTaskById(long id) {
        return subtaskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Subtask not found."));

    }

    @Override
    public Subtask update(@Valid SubtaskDto subTask) {
        Subtask fromDb = getSubTaskById(subTask.getId());
        if (fromDb != null) {
            if (subTask.getName() != null) {
                fromDb.setName(subTask.getName());
            }
            if (subTask.getDescription() != null) {
                fromDb.setDescription(subTask.getDescription());
            }
            if (subTask.isCompleted() != null) {
                fromDb.setCompleted(subTask.isCompleted());
            }

            Task task = taskRepository.findById(subTask.getTaskId()).orElseThrow(() -> new RecordNotFoundException("Task not found."));


            return subtaskRepository.save(fromDb);
        } else {
            throw new RecordNotFoundException("Subtask not found.");
        }
    }


    @Override
    public void deleteSubTaskById(long id) {
        boolean exists = subtaskRepository.existsById(id);
        if (exists) {
            subtaskRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Subtask not found.");
        }

    }

    @Override
    public List<Subtask> getAllSubTasks() {
        return subtaskRepository.findAll();
    }

    @Override
    public List<Subtask> getSubTaskByName(String name) {
        return subtaskRepository.findAllSubtasksByNameContainsIgnoreCase(name);

    }
}
