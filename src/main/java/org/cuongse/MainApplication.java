package org.cuongse;

import org.cuongse.exception.TaskNotFoundException;
import org.cuongse.model.Task;
import org.cuongse.model.TaskStatus;
import org.cuongse.repository.TaskRepositoryImpl;
import org.cuongse.service.TaskService;
import org.cuongse.service.TaskServiceImpl;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        TaskService taskService = new TaskServiceImpl(new TaskRepositoryImpl());

        if(args.length == 0){
            System.out.println("Please provide a command.");
            return;
        }

        String command = args[0];
        try {
            switch (command){
                case "add":
                    if(args.length < 2){
                        System.out.println("Please provide a task name.");
                        return;
                    }
                    Task newTask = taskService.addTask(args[1]);
                    System.out.println("Task added: " + newTask.getId());
                    break;

                case "update":
                    if(args.length < 3){
                        System.out.println("Usage: task-cli update <id> \"new description\"");
                        return;
                    }
                    int id = Integer.parseInt(args[1]);
                    String newDescription = args[2];
                    Task updatedTask = taskService.updateTask(id, newDescription);
                    System.out.println("Task updated: " + updatedTask.getId());
                    break;

                case "delete":
                    if(args.length < 2){
                        System.out.println("Please provide a task ID.");
                        return;
                    }
                    int taskId = Integer.parseInt(args[1]);
                    taskService.deleteTask(taskId);
                    System.out.println("Task removed: " + taskId);
                    break;
                case "mark-done":
                    if(args.length < 2){
                        System.out.println("Please provide a task ID.");
                        return;
                    }
                    int doneTaskId = Integer.parseInt(args[1]);
                    Task doneTask = taskService.markTaskAsDone(doneTaskId);
                    System.out.println("Task marked as done: " + doneTask.getId());
                    break;

                case "mark-in-progress":
                    if(args.length < 2){
                        System.out.println("Please provide a task ID.");
                        return;
                    }
                    int inProgressTaskId = Integer.parseInt(args[1]);
                    Task inProgressTask = taskService.markTaskAsInProgress(inProgressTaskId);
                    System.out.println("Task marked as in progress: " + inProgressTask.getId());
                    break;

                case "list":
                    if(args.length == 1){
                        List<Task> tasks = taskService.listAllTasks();
                        tasks.forEach(task -> System.out.println(task));
                    }else{
                        TaskStatus status = TaskStatus.fromString(args[1]);
                        List<Task> filtered = taskService.listTasksByStatus(status);
                        filtered.forEach(task -> System.out.println(task));
                    }
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }
        }catch(TaskNotFoundException e){
            System.out.println("Task not found: " + e.getMessage());
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid argument: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}