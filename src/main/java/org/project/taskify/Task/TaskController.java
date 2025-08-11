package org.project.taskify.Task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {
    

    @GetMapping("/tasks")
    public ArrayList<Task> getTasks()
    {
        return new ArrayList<>(Arrays.asList(new Task(), new Task()));
    }
}
