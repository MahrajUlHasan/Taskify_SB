package org.project.taskify.Task;

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
    @PostMapping("/tasks")
    public Task insertTask(@RequestBody Task task)
    {
        return taskService.insertTask(task);
    }
}
