package com.osu.BackEndToDoApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //primary key
    @NotNull
    private String name;
    private String description;
    private Boolean completed;

    private LocalDateTime created;
    private Priority priority;



    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task", fetch = FetchType.EAGER)
    private List<Subtask> subtasks;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task", fetch = FetchType.EAGER)
    private List<Category> category;

    // konstruktory, gettery, settery


    public Task() { //bezparametrický konstruktor
        subtasks = new ArrayList();
        category = new ArrayList();
        this.completed = false; //todo možná bez this?
    }

    //*public Task(long id, String name, String description, boolean completed, List<Subtask> subtasks, List<Category> category, LocalDateTime created, Priority priority) { //konstruktor s všemi parametry
        //this.id = id;
        //this.name = name;
        //this.description = description;
        //this.completed = completed;
        //this.subtasks = subtasks;  //todo možná = new Arraylist<>(); původní konstruktor   ASI SMAZAT?????????????
        //this.category = category;
        //this.created = created;
      //  this.priority=priority;
    //}
    public Task(long id, String name, String description, boolean completed, List<Subtask> subtasks, List<Category> category, LocalDateTime created, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.subtasks = subtasks != null ? subtasks : new ArrayList<>(); // Tento řádek zajišťuje, že subtasks bude vždy inicializován
        this.category = category != null ? category : new ArrayList<>(); // Tento řádek zajišťuje, že category bude vždy inicializován
        this.created = created;
        this.priority = priority;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) { //todo  možná smazat settery u subtasků a category
        this.subtasks = subtasks;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}




