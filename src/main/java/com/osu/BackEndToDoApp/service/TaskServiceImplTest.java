package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.TaskDto;
import com.osu.BackEndToDoApp.exception.RecordNotFoundException;
import com.osu.BackEndToDoApp.model.Category;
import com.osu.BackEndToDoApp.model.Priority;
import com.osu.BackEndToDoApp.model.Subtask;
import com.osu.BackEndToDoApp.model.Task;
import com.osu.BackEndToDoApp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {
    //using Mockito framework for mocking tests
    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setName("Task 1");
        taskDto.setDescription("Description 1");
        taskDto.setCompleted(false);
        taskDto.setCreated(LocalDateTime.now());
        taskDto.setPriority(Priority.LOW);
        // Nastavte další hodnoty, pokud je třeba

        Task expectedTask = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);

        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        Task result = taskService.createTask(taskDto);

        assertNotNull(result);
        assertEquals(expectedTask, result);
        verify(taskRepository, times(1)).save(any(Task.class));
    }
    @Test
    public void testGetTaskById() {
        Task expectedTask = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(expectedTask));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(expectedTask, result);
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTaskById_NotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> taskService.getTaskById(1L));
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindTasksByPriority() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        Task task2 = new Task(2L, "Task 2", "Description 2", false, null, null, LocalDateTime.now(), Priority.LOW);
        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskRepository.findAllTasksByPriority(Priority.LOW)).thenReturn(expectedTasks);

        List<Task> result = taskService.findTasksByPriority("low");

        assertNotNull(result);
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAllTasksByPriority(Priority.LOW);
    }

    @Test
    public void testUpdate() {
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Description", false, LocalDateTime.now(), Priority.MEDIUM);
        Task originalTask = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        Task expectedTask = new Task(1L, "Updated Task", "Updated Description", true, null, null, taskDto.getCreated(), Priority.MEDIUM);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(originalTask));
        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        Task result = taskService.update(taskDto);

        assertNotNull(result);
        assertEquals(expectedTask, result);
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testDeleteTaskById() {
        when(taskRepository.existsById(1L)).thenReturn(true);

        taskService.deleteTaskById(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTaskById_NotFound() {
        when(taskRepository.existsById(1L)).thenReturn(false);

        assertThrows(RecordNotFoundException.class, () -> taskService.deleteTaskById(1L));
        verify(taskRepository, times(1)).existsById(1L);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        Task task2 = new Task(2L, "Task 2", "Description 2", false, null, null, LocalDateTime.now(), Priority.MEDIUM);
        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(expectedTasks);

        List<Task> result = taskService.getAllTasks();

        assertNotNull(result);
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTaskByName() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        List<Task> expectedTasks = Arrays.asList(task1);

        when(taskRepository.findAllTasksByNameContainsIgnoreCase("Task")).thenReturn(expectedTasks);

        List<Task> result = taskService.getTaskByName("Task");

        assertNotNull(result);
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAllTasksByNameContainsIgnoreCase("Task");
    }

    @Test
    public void testGetAllTasksSortedByPriority() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        Task task2 = new Task(2L, "Task 2", "Description 2", false, null, null, LocalDateTime.now(), Priority.MEDIUM);
        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskRepository.findAllByOrderByPriorityAsc()).thenReturn(expectedTasks);

        List<Task> result = taskService.getAllTasksSortedByPriority(true);

        assertNotNull(result);
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAllByOrderByPriorityAsc();
    }
    @Test
    public void testGetAllTasksSortedByCategory() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        Task task2 = new Task(2L, "Task 2", "Description 2", false, null, null, LocalDateTime.now(), Priority.MEDIUM);
        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskRepository.findAllByOrderByCategoryAsc()).thenReturn(expectedTasks);

        List<Task> result = taskService.getAllTasksSortedByCategory(true);

        assertNotNull(result);
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAllByOrderByCategoryAsc();
    }

    @Test
    public void testGetAllTasksSortedByName() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false, null, null, LocalDateTime.now(), Priority.LOW);
        Task task2 = new Task(2L, "Task 2", "Description 2", false, null, null, LocalDateTime.now(), Priority.MEDIUM);
        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskRepository.findAllByOrderByNameAsc()).thenReturn(expectedTasks);

        List<Task> result = taskService.getAllTasksSortedByName(true);

        assertNotNull(result);
        assertEquals(expectedTasks, result);
        verify(taskRepository, times(1)).findAllByOrderByNameAsc();
    }

    @Test
    public void testSearchTasksAndSubtaskandCategorysByName() {
        // Vytvořte nejprve proměnné task1, task2 a task3, které budou použity v konstruktorech Category a Subtask
        Task task1 = new Task(1L, "TestTask1", "Description1", false, null, null, LocalDateTime.now(), Priority.HIGH);
        Task task2 = new Task(2L, "TestTask2", "Description2", false, null, null, LocalDateTime.now(), Priority.MEDIUM);
        Task task3 = new Task(3L, "UnrelatedTask", "Description3", false, null, null, LocalDateTime.now(), Priority.LOW);

        Category category1 = new Category(1L, "TestCategory", "Description1", task1);
        Category category2 = new Category(2L, "AnotherCategory", "Description2", task2);

        Subtask subtask1 = new Subtask(1L, "TestSubtask", false, "DescriptionSUbtask1", task1);


        // Aktualizujte seznamy subtasks a categories už existujících proměnných task1 a task2
        task1.setSubtasks(Arrays.asList(subtask1));
        task1.setCategory(Arrays.asList(category1));
        task2.setCategory(Arrays.asList(category2));

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2, task3));

        String searchTerm = "test";
        List<Task> result = taskService.searchTasksAndSubtaskandCategorysByName(searchTerm);

        assertEquals(2, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));
    }

}






