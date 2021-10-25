package com.example.todo.controller;

import com.example.todo.domain.RestTask;
import com.example.todo.domain.Task;
import com.example.todo.repo.TaskRepo;
import com.example.todo.service.TaskService;
import com.example.todo.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    ResponseEntity<?> allTasks(){
        final List<Task> tasks = service.getAll();
        return tasks != null && !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    ResponseEntity<?> newTask(@RequestBody Task task){
        long id;
        try {
            id = service.create(task).getId();
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actual")
    ResponseEntity<?> actual() {
        List<RestTask> actualTasks = service.actual();
        return actualTasks != null && !actualTasks.isEmpty()
                ? new ResponseEntity<>(actualTasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/irrelevant")
    ResponseEntity<?>  irrelevant() {
        List<RestTask> irrelevantTasks = service.irrelevant();
        return irrelevantTasks != null && !irrelevantTasks.isEmpty()
                ? new ResponseEntity<>(irrelevantTasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/tasks/{id}")
    ResponseEntity<?> descriptionUpdate(@PathVariable Long id, @RequestBody String newDescription) {
        String result;
        try {
            result = service.update(id, newDescription);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/tasks/{id}")
    ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            service.deleteOne(id);
            return new ResponseEntity<>("Задача была удалена", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
