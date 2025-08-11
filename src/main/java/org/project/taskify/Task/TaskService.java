package org.project.taskify.Task;


import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //TODO : do not return the Task class . change to a class that can be handed to client . will change later
    public List<Task> getAllTasks()
    {
     return taskRepository.findAll();
    }

    public Task insertTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Task name is required");
        }

        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Task description is required");
        }

        Date now = new Date();
        if (task.getCreatedDate() == null) {
            task.setCreatedDate(now);
        }
        task.setUpdatedDate(now);

        if (task.getStatus() == null || task.getStatus().trim().isEmpty()) {
            task.setStatus("Open");
        }

        if (task.getPriority() == null || task.getPriority().trim().isEmpty()) {
            task.setPriority("Low");
        }

        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save task: " + e.getMessage(), e);
        }
    }

//    public void deleteTask() {
//        taskRepository.delete();
//    }
}
