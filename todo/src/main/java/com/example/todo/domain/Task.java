package com.example.todo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String taskName;

    private String taskDescription;

    private Date dateOfOperation;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public RestTask collect(){
        RestTask restTask = new RestTask();
        restTask.setId(this.getId());
        restTask.setTaskName(this.getTaskName());
        restTask.setTaskDescription(this.getTaskDescription());
        return restTask;
    }
}
