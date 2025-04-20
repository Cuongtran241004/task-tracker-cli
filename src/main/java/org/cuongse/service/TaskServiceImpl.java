package org.cuongse.service;

import org.cuongse.exception.TaskNotFoundException;
import org.cuongse.model.Task;
import org.cuongse.model.TaskStatus;
import org.cuongse.repository.TaskRepository;

import java.util.List;

public class TaskServiceImpl implements TaskService{
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task addTask(String description) {
       List<Task> tasks = repository.getAllTasks();

       // Generate a new ID for the task
       int id = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
       Task task = new Task(id, description);
       tasks.add(task);

       // Save the updated list of tasks to the repository
       repository.saveTasks(tasks);
       return task;
    }

    @Override
    public Task updateTask(int id, String newDescription) {
        List<Task> tasks = repository.getAllTasks();
        // Find the task with the given ID and update its description
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                // Save the updated list of tasks to the repository
                repository.saveTasks(tasks);
                return task;
            }
        }
        throw new TaskNotFoundException("Task not found with ID: " + id);
    }

    @Override
    public void deleteTask(int id) {
        List<Task> tasks = repository.getAllTasks();
        // Remove the task with the given ID
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        // If no task was removed, throw an exception
        if (!removed) {
            throw new TaskNotFoundException("Task not found with ID: " + id);
        }
        // Save the updated list of tasks to the repository
        repository.saveTasks(tasks);
    }

    @Override
    public Task markTaskAsDone(int id) {
        return updateStatus(id, TaskStatus.DONE);
    }

    @Override
    public Task markTaskAsInProgress(int id) {
        return updateStatus(id, TaskStatus.IN_PROGRESS);
    }

    @Override
    public List<Task> listAllTasks() {
        return repository.getAllTasks();
    }

    @Override
    public List<Task> listTasksByStatus(TaskStatus status) {
        List<Task> tasks = repository.getAllTasks();
        // Filter tasks by the given status
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();

        return filteredTasks;
    }

    private Task updateStatus(int id, TaskStatus status) {
        List<Task> tasks = repository.getAllTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                // Save the updated list of tasks to the repository
                repository.saveTasks(tasks);
                return task;
            }
        }
        throw new TaskNotFoundException("Task not found with ID: " + id);
    }
}
