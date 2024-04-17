package com.todoapp.managers;

import com.todoapp.models.Task;
import com.todoapp.observers.ActivityLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
  private TaskManager taskManager;

  @BeforeEach
  void setUp() {
    taskManager = TaskManager.getInstance();
    taskManager.clearTasks(); // Make sure to clear tasks before each test
  }

  @Test
  void listTasksWithNoTasks() {
    assertTrue(taskManager.listTasks(Optional.empty(), Optional.empty()).isEmpty(), "List should be empty when no tasks are added.");
  }

  @Test
  void listTasksWithFilterAndSort() {
    Task task1 = new Task("Task 1", LocalDateTime.now().plusDays(1), new HashSet<>(Arrays.asList("home")));
    Task task2 = new Task("Task 2", LocalDateTime.now().plusDays(2), new HashSet<>(Arrays.asList("work")));
    taskManager.addTask(task1);
    taskManager.addTask(task2);

    List<Task> filteredTasks = taskManager.listTasks(Optional.of(new HashSet<>(Arrays.asList("home"))),
        Optional.of(Comparator.comparing(Task::getDescription)));
    assertEquals(1, filteredTasks.size(), "Should only retrieve one task with 'home' tag.");
    assertEquals("Task 1", filteredTasks.get(0).getDescription(), "Filtered task should be 'Task 1'.");
  }

  @Test
  void checkTaskCompletionTime() {
    Task task = new Task("Complete report", LocalDateTime.now().plusDays(1), new HashSet<>(Arrays.asList("work")));
    taskManager.addTask(task);
    assertNull(task.getCompletionTime(), "Completion time should be null for incomplete tasks.");

    task.setCompleted(true);
    assertNotNull(task.getCompletionTime(), "Completion time should be set once the task is completed.");
  }

  @Test
  void getStatisticsWithinTimePeriod() {
    LocalDateTime now = LocalDateTime.now();
    Task task1 = new Task("Task 1", now.plusDays(2), new HashSet<>(Arrays.asList("home")));
    Task task2 = new Task("Task 2", now.plusDays(3), new HashSet<>(Arrays.asList("work")));
    task1.setCompleted(true);  // Set this task as completed immediately for testing
    taskManager.addTask(task1);
    taskManager.addTask(task2);

    Map<String, Integer> stats = taskManager.getStatistics(now.minusDays(1), now.plusDays(1));
    assertEquals(2, stats.getOrDefault("Added", 0), "Two tasks should be counted as added.");
    assertEquals(1, stats.getOrDefault("Completed", 0), "One task should be counted as completed.");
  }

  @Test
  void verifyActivityLogs() {
    ActivityLogger logger = new ActivityLogger();  // Assuming you have a suitable constructor or setup
    taskManager.getEventManager().subscribe("add", logger);

    Task task = new Task("New task", LocalDateTime.now().plusDays(5), new HashSet<>(Arrays.asList("urgent")));
    taskManager.addTask(task);

    List<String> logs = logger.getLogs(Optional.empty(), Optional.empty());
    assertFalse(logs.isEmpty(), "There should be log entries after adding a task.");
    assertTrue(logs.get(0).contains("Added Task"), "Log entry should contain 'Added Task'.");
  }
}
