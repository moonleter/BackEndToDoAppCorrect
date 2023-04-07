package com.osu.BackEndToDoApp.service;

import com.osu.BackEndToDoApp.dto.SubtaskDto;
import com.osu.BackEndToDoApp.model.Subtask;
import com.osu.BackEndToDoApp.model.Task;
import jakarta.validation.Valid;

import java.util.List;

public interface SubtaskService {
    Subtask createSubTask(@Valid SubtaskDto newSubTask); //vytvoření nového subtasku

    Subtask getSubTaskById(long id);//vytahnuti subtasku podle ID

    Subtask update(@Valid SubtaskDto subTask); //aktualizace subtasku

    void deleteSubTaskById(long id); //smazání subtasku podle ID

    List<Subtask> getAllSubTasks(); //vrátí všechny subtasky

    List<Subtask> getSubTaskByName(String name); //vrátí subtask podle jména

}
