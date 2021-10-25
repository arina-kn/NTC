package com.example.todo.repo;

import com.example.todo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long>{
    List<Task> findByDateOfOperationAfter(Date date);
    List<Task> findByDateOfOperationBefore(Date date);
}
