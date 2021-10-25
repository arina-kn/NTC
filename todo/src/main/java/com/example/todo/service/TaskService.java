package com.example.todo.service;

import com.example.todo.domain.RestTask;
import com.example.todo.domain.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    Task create(Task newTask);
    List<Task> getAll();
    void deleteOne(long id);
    List<RestTask> actual();
    List<RestTask> irrelevant();
    String update(long id, String newDescription);
}
