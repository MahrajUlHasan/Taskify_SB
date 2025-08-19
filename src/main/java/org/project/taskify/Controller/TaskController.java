package org.project.taskify.Controller;

import org.project.taskify.Model.Task;
import org.project.taskify.Service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks()
    {
        return taskService.getAllTasks();

    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Integer id)
    {
        try {
            return taskService.getTaskById(id);
        }
        catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        return null;
        }
    }
    @PostMapping("/tasks")
    public Task insertTask(@RequestBody Task task)
    {
           return taskService.insertTask(task);


    }
}
