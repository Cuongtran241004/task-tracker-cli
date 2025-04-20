package org.cuongse.service;

import org.cuongse.model.Task;
import org.cuongse.model.TaskStatus;

import java.util.List;

public interface TaskService {
    Task addTask(String description);
    Task updateTask(int id, String newDescription);
    void deleteTask(int id);
    Task markTaskAsDone(int id);
    Task markTaskAsInProgress(int id);
    List<Task> listAllTasks();
    List<Task> listTasksByStatus(TaskStatus status);
}
