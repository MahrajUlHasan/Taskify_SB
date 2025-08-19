package org.project.taskify.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;


    @OneToMany (mappedBy = "category")
    private List<Task> tasks;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(Id, category.Id) && Objects.equals(tasks, category.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, tasks);
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + Id +
                ", tasks=" + tasks +
                '}';
    }
}
