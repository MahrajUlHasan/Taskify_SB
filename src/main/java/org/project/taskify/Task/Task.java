package org.project.taskify.Task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class Task {
    private String name;
    private String description;
    private String status;
    private String priority;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;
    private String assignee;
    private String reporter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date updatedDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    private Priority priority;

    public Task() {
    }

    public Task(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public Task(String assignee,
                Date createdDate,
                String description,
                Date dueDate,
                int id,
                String name,
                String priority,
                String reporter,
                String status,
                Date updatedDate) {
        this.assignee = assignee;
        this.createdDate = createdDate;
        this.description = description;
        this.dueDate = dueDate;
        this.id = id;
        this.name = name;
        this.priority = priority;//todo : make Enum
        this.reporter = reporter;
        this.status = status;
        this.updatedDate = updatedDate;
    }

    //without id
    public Task(String assignee,
                Date createdDate,
                String description,
                Date dueDate,
                String name,
                String priority,
                String reporter,
                String status,
                Date updatedDate) {
        this.assignee = assignee;
        this.createdDate = createdDate;
        this.description = description;
        this.dueDate = dueDate;
        this.name = name;
        this.priority = priority;
        this.reporter = reporter;
        this.status = status;
        this.updatedDate = updatedDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getReporter() {
        return reporter;
    }

    public String getStatus() {
        return status;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Task [name=" + name + ", description=" + description + ", status=" + status + ", priority=" + priority
                + ", dueDate=" + dueDate + ", assignee=" + assignee + ", reporter=" + reporter + ", createdDate="
                + createdDate + ", updatedDate=" + updatedDate + ", id=" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(status, task.status) && Objects.equals(priority, task.priority) && Objects.equals(dueDate, task.dueDate) && Objects.equals(assignee, task.assignee) && Objects.equals(reporter, task.reporter) && Objects.equals(createdDate, task.createdDate) && Objects.equals(updatedDate, task.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, priority, dueDate, assignee, reporter, createdDate, updatedDate, id);
    }
}
