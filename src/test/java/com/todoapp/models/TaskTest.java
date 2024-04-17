package com.todoapp.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

  @Test
  void taskCreation() {
    Task task = new Task("Review design doc", LocalDateTime.now().plusDays(2), Collections.singleton("work"));
    assertNotNull(task.getId(), "Task ID should not be null after creation.");
    assertEquals("Review design doc", task.getDescription(), "Description should match the one provided at creation.");
    assertFalse(task.isCompleted(), "New task should not be completed.");
  }

  @Test
  void setAndGetCompletion() {
    Task task = new Task("Write unit tests", LocalDateTime.now().plusDays(1), Collections.singleton("testing"));
    task.setCompleted(true);
    assertTrue(task.isCompleted(), "Task should be marked as completed.");
    assertNotNull(task.getCompletionTime(), "Completion time should be set when task is completed.");
  }

  @Test
  void modifyTaskProperties() {
    Task task = new Task("Attend meeting", LocalDateTime.now(), Collections.singleton("office"));
    LocalDateTime newDeadline = LocalDateTime.now().plusDays(5);

    task.setDescription("Attend annual meeting");
    task.setDeadline(newDeadline);

    assertEquals("Attend annual meeting", task.getDescription(), "Task description should be updated.");

    // Check that the deadline is set correctly by comparing LocalDate parts
    assertEquals(newDeadline.toLocalDate(), task.getDeadline().toLocalDate(), "Deadline date should be correctly updated.");
  }
}
