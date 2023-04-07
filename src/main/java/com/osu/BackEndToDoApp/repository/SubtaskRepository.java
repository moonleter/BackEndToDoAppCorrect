package com.osu.BackEndToDoApp.repository;

import com.osu.BackEndToDoApp.model.Subtask;
import com.osu.BackEndToDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    List<Subtask> findAllSubtasksByNameContainsIgnoreCase(String name);

}
