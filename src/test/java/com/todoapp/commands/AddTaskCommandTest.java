package com.todoapp.commands;

import com.todoapp.factories.SimpleTaskFactory;
import com.todoapp.managers.TaskManager;
import com.todoapp.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class AddTaskCommandTest {
  @Mock
  private TaskManager taskManager;
  private SimpleTaskFactory factory;
  private AddTaskCommand command;
  private final InputStream systemIn = System.in;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    factory = new SimpleTaskFactory(); // Assuming factory just creates tasks
    String input = "Complete documentation\n2024-05-10\nwork";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    command = new AddTaskCommand(scanner, factory, taskManager);
  }

  @Test
  void executeAddTask() {
    command.execute();
    verify(taskManager, times(1)).addTask(any(Task.class));
  }

  @BeforeEach
  void resetSystemIn() {
    System.setIn(systemIn);
  }
}
