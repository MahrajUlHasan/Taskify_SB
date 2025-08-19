package org.project.taskify.Exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String s) {
        super(s);
    }
}
