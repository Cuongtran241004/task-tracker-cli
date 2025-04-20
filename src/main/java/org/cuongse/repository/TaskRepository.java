package org.cuongse.repository;

import org.cuongse.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> getAllTasks();
    void saveTasks(List<Task> tasks);
}
