package org.project.taskify.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task validTask;

    @BeforeEach
    void setUp() {
        validTask = new Task();
        validTask.setName("Test Task");
        validTask.setDescription("Test Description");
    }

    @Test
    void insertTask_WithValidTask_ShouldReturnSavedTask() {
        // Arrange
        Task savedTask = new Task();
        savedTask.setId(1);
        savedTask.setName("Test Task");
        savedTask.setDescription("Test Description");
        savedTask.setStatus("Open");
        savedTask.setPriority("Medium");
        savedTask.setCreatedDate(new Date());
        savedTask.setUpdatedDate(new Date());

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        Task result = taskService.insertTask(validTask);

        // Assert
        assertNotNull(result);
        assertEquals("Test Task", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals("Open", result.getStatus());
        assertEquals("Medium", result.getPriority());
        assertNotNull(result.getCreatedDate());
        assertNotNull(result.getUpdatedDate());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void insertTask_WithNullTask_ShouldThrowIllegalArgumentException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> taskService.insertTask(null)
        );
        assertEquals("Task cannot be null", exception.getMessage());
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void insertTask_WithNullName_ShouldThrowIllegalArgumentException() {
        // Arrange
        validTask.setName(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> taskService.insertTask(validTask)
        );
        assertEquals("Task name is required", exception.getMessage());
        // verify the save method was never called with any Task object
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void insertTask_WithEmptyName_ShouldThrowIllegalArgumentException() {
        // Arrange
        validTask.setName("   ");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> taskService.insertTask(validTask)
        );
        assertEquals("Task name is required", exception.getMessage());
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void insertTask_WithNullDescription_ShouldThrowIllegalArgumentException() {
        // Arrange
        validTask.setDescription(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> taskService.insertTask(validTask)
        );
        assertEquals("Task description is required", exception.getMessage());
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void insertTask_WithEmptyDescription_ShouldThrowIllegalArgumentException() {
        // Arrange
        validTask.setDescription("   ");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> taskService.insertTask(validTask)
        );
        assertEquals("Task description is required", exception.getMessage());
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void insertTask_ShouldSetDefaultStatusWhenNull() {
        // Arrange
        validTask.setStatus(null);
        when(taskRepository.save(any(Task.class))).thenReturn(validTask);

        // Act
        taskService.insertTask(validTask);

        // Assert
        assertEquals("Open", validTask.getStatus());
        verify(taskRepository, times(1)).save(validTask);
    }

    @Test
    void insertTask_ShouldSetDefaultPriorityWhenNull() {
        // Arrange
        validTask.setPriority(null);
        when(taskRepository.save(any(Task.class))).thenReturn(validTask);

        // Act
        taskService.insertTask(validTask);

        // Assert
        assertEquals("Medium", validTask.getPriority());
        verify(taskRepository, times(1)).save(validTask);
    }

    @Test
    void insertTask_ShouldSetTimestamps() {
        // Arrange
        when(taskRepository.save(any(Task.class))).thenReturn(validTask);

        // Act
        taskService.insertTask(validTask);

        // Assert
        assertNotNull(validTask.getCreatedDate());
        assertNotNull(validTask.getUpdatedDate());
        verify(taskRepository, times(1)).save(validTask);
    }

    @Test
    void insertTask_ShouldNotOverrideExistingCreatedDate() {
        // Arrange
        Date existingCreatedDate = new Date(System.currentTimeMillis() - 86400000); // 1 day ago
        validTask.setCreatedDate(existingCreatedDate);
        when(taskRepository.save(any(Task.class))).thenReturn(validTask);

        // Act
        taskService.insertTask(validTask);

        // Assert
        assertEquals(existingCreatedDate, validTask.getCreatedDate());
        assertNotNull(validTask.getUpdatedDate());
        verify(taskRepository, times(1)).save(validTask);
    }

    @Test
    void insertTask_WithRepositoryException_ShouldThrowRuntimeException() {
        // Arrange
        when(taskRepository.save(any(Task.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> taskService.insertTask(validTask)
        );
        assertTrue(exception.getMessage().contains("Failed to save task"));
        assertTrue(exception.getMessage().contains("Database error"));
        verify(taskRepository, times(1)).save(any(Task.class));
    }
}
