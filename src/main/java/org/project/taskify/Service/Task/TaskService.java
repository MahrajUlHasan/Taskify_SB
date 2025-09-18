package org.project.taskify.Service;


import lombok.RequiredArgsConstructor;
import org.project.taskify.Exceptions.TaskNotFoundException;
import org.project.taskify.Model.Category;
import org.project.taskify.Model.Task;
import org.project.taskify.Repository.TaskRepository;
import org.project.taskify.Repository.CategoryRepository;
import org.project.taskify.Requests.AddTaskRequest;
import org.project.taskify.Requests.TaskUpdateRequest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final StringHttpMessageConverter stringHttpMessageConverter;

//    public TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }

    //TODO : do not return the Task class . change to a class that can be handed to client . will change later
    public List<Task> getAllTasks()
    {
     return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
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
    public Task AddTask(AddTaskRequest task) {

        Category category = Optional.ofNullable(categoryRepository.findByName(task.getCategory().getName())).orElseGet(()->
                {
                    Category newCategory = new Category(task.getCategory().getName());
                    return categoryRepository.save(newCategory);
                }
        );

        task.setCategory(category);
        return insertTask(createTask(task, category));

    }

    private Task createTask(AddTaskRequest task , Category category) {
        return new Task(
                task.getAssignee(),
                task.getUpdatedDate(),
                task.getStatus(),
                task.getReporter(),
                task.getPriority(),
                task.getName(),
                task.getDueDate(),
                task.getDescription(),
                task.getCreatedDate(),
                category
        );
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.findById(id).ifPresentOrElse(taskRepository::delete, ()->{
            throw new TaskNotFoundException("Task with id " + id + " not found");
        });
    }

    public List<Task> getTasksById(List<Long> id) throws IllegalArgumentException{
        return taskRepository.findAllById(id);
    }

    @Override
    public List<Task> getTasksByCategory(Long categoryId) {
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
    public Long countTasks() {
        return taskRepository.count();

    }

    @Override
    public Long countTasksByCategory(Long categoryId) {
        return taskRepository.countTaskByCategoryId(categoryId);
    }

    @Override
    public Long countTasksByStatus(String status) {
        return taskRepository.countTasksByStatus(status);
    }

    @Override
    public Long countTasksByPriority(String priority) {
        return taskRepository.countTasksByPriority(priority);
    }

    @Override
    public Long countTasksByDueDate(String dueDate) {
        return 0L;
    }

    @Override
    public Long countTaskByCatagoryName(String categoryName) {
        return 0L;
    }

    @Override
    public Task updateTask(TaskUpdateRequest task, Long id) {
        return taskRepository.findById(id)
                .map(existingTask -> updateExistingTask(existingTask, task))
                .map(taskRepository::save)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    private Task updateExistingTask(Task existingTask , TaskUpdateRequest request) {
        existingTask.setName(request.getName());
        existingTask.setDescription(request.getDescription());
        existingTask.setStatus(request.getStatus());
        existingTask.setPriority(request.getPriority());
        existingTask.setDueDate(request.getDueDate());
        existingTask.setAssignee(request.getAssignee());
        existingTask.setReporter(request.getReporter());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingTask.setCategory(category);
        existingTask.setUpdatedDate(new Date());
        return existingTask;
    }



}
