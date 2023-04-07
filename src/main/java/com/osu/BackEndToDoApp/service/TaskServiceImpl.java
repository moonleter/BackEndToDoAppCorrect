package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.TaskDto;
import com.osu.BackEndToDoApp.exception.RecordNotFoundException;
import com.osu.BackEndToDoApp.model.Priority;
import com.osu.BackEndToDoApp.model.Task;
import com.osu.BackEndToDoApp.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(@Valid TaskDto validateTask) {
        Task newTask = new Task(
                validateTask.getId(),
                validateTask.getName(),
                validateTask.getDescription(),
                validateTask.isCompleted(),
                null, // todo Set subtasks and categories to null or pass them from ValidateTask if needed
                null,
                validateTask.getCreated(),
                validateTask.getPriority()


        );
        newTask.setCompleted(false); // aby defaultně nebyl completed task
        newTask.setCreated(LocalDateTime.now());
        Task ret = taskRepository.save(newTask);
        return ret;
    }


    @Override
    public Task getTaskById(long id) {
        Task ret = taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task not found."));
        return ret;
    }

    @Override
    public List<Task> findTasksByPriority(String priority) {
        return taskRepository.findAllTasksByPriority(Priority.valueOf(priority.toUpperCase()));
    }


    @Override
    public Task update(@Valid TaskDto task) {
        Task fromDb = getTaskById(task.getId());
        if (fromDb != null) {
            if (task.getName() != null) {
                fromDb.setName(task.getName());
            }
            if (task.getCreated() != null) {
                fromDb.setCreated(task.getCreated());
            }
            if (task.isCompleted() != null) {
                fromDb.setCompleted(task.isCompleted());
            }
            if (task.getDescription() != null) {
                fromDb.setDescription(task.getDescription());
            }
            if (task.getPriority() != null) {
                fromDb.setPriority(task.getPriority());
            }
            if (task.getCategory()!= null) {
                fromDb.setCategory(task.getCategory());
            }

            return taskRepository.save(fromDb);
        } else {
            throw new RecordNotFoundException("Task not found.");
        }
    }


    @Override
    public void deleteTaskById(long id) {
        boolean exists = taskRepository.existsById(id);
        if (exists) {
            taskRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Task not found.");
        }

    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTaskByName(String name) {
        return taskRepository.findAllTasksByNameContainsIgnoreCase(name);
    }

    @Override
    public List<Task> getAllTasksSortedByPriority(boolean ascending) {
        return ascending ? taskRepository.findAllByOrderByPriorityAsc() : taskRepository.findAllByOrderByPriorityDesc();
    }

    @Override
    public List<Task> getAllTasksSortedByCategory(boolean ascending) {
        return ascending ? taskRepository.findAllByOrderByCategoryAsc() : taskRepository.findAllByOrderByCategoryDesc();
    }

    @Override
    public List<Task> getAllTasksSortedByName(boolean ascending) {
        return ascending ? taskRepository.findAllByOrderByNameAsc() : taskRepository.findAllByOrderByNameDesc();
    }

    @Override
    public List<Task> searchTasksAndSubtaskandCategorysByName(String name) {
        List<Task> tasks = taskRepository.findAll();
        List<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            boolean matchesTask = task.getName().toLowerCase().contains(name.toLowerCase());
            boolean matchesSubtask = task.getSubtasks().stream().anyMatch(subtask -> subtask.getName().toLowerCase().contains(name.toLowerCase()));
            boolean matchesCategory = task.getCategory().stream().anyMatch(category -> category.getName().toLowerCase().contains(name.toLowerCase()));

            if (matchesTask || matchesSubtask || matchesCategory) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }


}
