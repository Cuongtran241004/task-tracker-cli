package org.cuongse.repository;

import org.cuongse.model.Task;
import org.cuongse.model.TaskStatus;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TaskRepositoryImpl implements TaskRepository{
private static final String FILE_PATH = "task.json";

    @Override
    public List<Task> getAllTasks() {
        List<Task> listAllTasks = new ArrayList<>();

        // TODO: Check if the file exists
        if(!Files.exists(Paths.get(FILE_PATH))){
            return listAllTasks;
        }

        // TODO: Read from file and parse JSON to Task objects
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            // Step 1: Read the file line by line, using StringBuilder
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // Step 2: Convert the StringBuilder to a String
            String content = sb.toString();
            if(content.isEmpty()){
                return listAllTasks;
            }

            // Step 3: Parse each line as a Task object
            String[] jsonTasks = content.split(Pattern.quote("},{"));
            for(String raw : jsonTasks){
                // Step 4: Clean up the JSON string
                String json = raw.replace("{", "").replace("}", "")
                        .replace("[", "").replace("]", "");

                // Step 5: Split the JSON string into key-value pairs
                Map<String, String> map = new HashMap<>();
                for(String pair : json.split(",")){
                    String[] keyValue = pair.split(":");
                    if(keyValue.length == 2){
                        String key = keyValue[0].trim().replace("\"", "");
                        String value = keyValue[1].trim().replace("\"", "");
                        map.put(key, value);
                    }
                }

                // Step 6: Create a Task object and add it to the list
                Task task = new Task(Integer.parseInt(map.get("id")),
                        map.get("description"));
                task.setStatus(TaskStatus.fromString(map.get("status")));
                listAllTasks.add(task);
            }
        }catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
        return listAllTasks;
    }

    @Override
    public void saveTasks(List<Task> tasks) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Step 1: Check if the list is empty
            if(tasks.isEmpty()){
                writer.write("[]");
                return;
            }
            // Step 2: Write the list of tasks to the file
            writer.write("[");

            // Step 3: Iterate through the list of tasks and write each task as a JSON object
            for(int i = 0; i < tasks.size(); i++){
                Task t = tasks.get(i);
                writer.write(String.format("{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\"}",
                        t.getId(), t.getDescription(), t.getStatus()));
                if (i < tasks.size() - 1) writer.write(",");
            }

            // Step 4: Close the JSON array
            writer.write("]");
        }catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
