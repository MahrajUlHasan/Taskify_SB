package org.project.taskify.Service;

import org.project.taskify.Model.Task;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITaskService {
    public List<Task> getAllTasks();
    public Task getTaskById(Integer id);
    public Task insertTask(@RequestBody Task task);
    public void deleteTask(Integer id);
    public List<Task> getTasksById(List<Integer> id) throws IllegalArgumentException;
    public List<Task> getTasksByCategory(Integer categoryId);
    public List<Task> getTasksByStatus(String status);
    public List<Task> getTasksByPriority(String priority);
    public List<Task> getTasksByDueDate(String dueDate);
    public void updateTask(Task task);
}
