package org.project.taskify.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

//    private Priority priority;

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
