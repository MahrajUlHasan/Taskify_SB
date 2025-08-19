package org.project.taskify.Service;


import org.project.taskify.Exceptions.TaskNotFoundException;
import org.project.taskify.Model.Task;
import org.project.taskify.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService implements ITaskService{
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //TODO : do not return the Task class . change to a class that can be handed to client . will change later
    public List<Task> getAllTasks()
    {
     return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
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

    @Override
    public void deleteTask(Integer id) {
        taskRepository.findById(id).ifPresentOrElse(taskRepository::delete, ()->{
            throw new TaskNotFoundException("Task with id " + id + " not found");
        });
    }

    public List<Task> getTasksById(List<Integer> id) throws IllegalArgumentException{
        return taskRepository.findAllById(id);
    }

    @Override
    public List<Task> getTasksByCategory(Integer categoryId) {
        return List.of();
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return List.of();
    }

    @Override
    public List<Task> getTasksByPriority(String priority) {
        return List.of();
    }

    @Override
    public List<Task> getTasksByDueDate(String dueDate) {
        return List.of();
    }

    @Override
    public void updateTask(Task task) {
        insertTask(task);
    }

}
