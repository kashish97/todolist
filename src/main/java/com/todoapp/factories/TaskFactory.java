package com.todoapp.factories;

import com.todoapp.models.Task;
import java.time.LocalDateTime;
import java.util.Set;

public interface TaskFactory {
  Task createTask(String description, LocalDateTime deadline, Set<String> tags);
}

