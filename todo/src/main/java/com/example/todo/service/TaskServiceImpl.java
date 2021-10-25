package com.example.todo.service;

import com.example.todo.domain.RestTask;
import com.example.todo.domain.Task;
import com.example.todo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private final TaskRepo repository;

    public TaskServiceImpl(TaskRepo repository) {
        this.repository = repository;
    }

    @Override
    public Task create(Task newTask) {
        return repository.save(newTask);
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteOne(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<RestTask> actual() {
        List<Task> tasks = repository.findByDateOfOperationBefore(new java.util.Date());
        List<RestTask> actualTasks = tasks
                .stream()
                .map(n -> n.collect())
                .collect(Collectors.toList());
        return actualTasks;
    }

    @Override
    public List<RestTask> irrelevant() {
        List<Task> tasks = repository.findByDateOfOperationAfter(new java.util.Date());
        List<RestTask> irrelevantTasks = tasks
                .stream()
                .map(n -> n.collect())
                .collect(Collectors.toList());
        return irrelevantTasks;
    }

    @Override
    public String update(long id, String newDescription) {
        Task task = repository.getById(id);
        task.setTaskDescription(newDescription);
        return repository.save(task).getTaskDescription();
    }


}
