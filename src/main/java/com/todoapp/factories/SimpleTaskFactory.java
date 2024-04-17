package com.todoapp.factories;

import com.todoapp.models.Task;
import java.time.LocalDateTime;
import java.util.Set;

public class SimpleTaskFactory implements TaskFactory {

  public Task createTask(String description, LocalDateTime deadline, Set<String> tags) {
    return new Task(description, deadline, tags);
  }
}
