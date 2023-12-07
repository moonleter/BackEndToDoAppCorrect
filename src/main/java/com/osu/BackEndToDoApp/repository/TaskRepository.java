package com.osu.BackEndToDoApp.repository;

import com.osu.BackEndToDoApp.model.Priority;
import com.osu.BackEndToDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllTasksByNameContainsIgnoreCase(String name);

    List<Task> findAllTasksByPriority(Priority priority);

    List<Task> findAllByOrderByPriorityAsc();

    List<Task> findAllByOrderByPriorityDesc();

    List<Task> findAllByOrderByCategoryAsc();

    List<Task> findAllByOrderByCategoryDesc();

    List<Task> findAllByOrderByNameAsc();

    List<Task> findAllByOrderByNameDesc();


}
