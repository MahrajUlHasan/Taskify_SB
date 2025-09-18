package org.project.taskify.Service;

import org.project.taskify.Model.Task;
import org.project.taskify.Requests.AddTaskRequest;
import org.project.taskify.Requests.TaskUpdateRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task insertTask(@RequestBody Task task);
    Task AddTask(AddTaskRequest task);
    List<Task> getTasksById(List<Long> id) ;
    List<Task> getTasksByCategory(Long categoryId);
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksByPriority(String priority);
    List<Task> getTasksByDueDate(String dueDate);
    Long countTasks();
    Long countTasksByCategory(Long categoryId);
    Long countTasksByStatus(String status);
    Long countTasksByPriority(String priority);
    Long countTasksByDueDate(String dueDate);
    Long countTaskByCatagoryName(String categoryName);
    Task updateTask(TaskUpdateRequest task , Long id);
    void deleteTask(Long id);
}
