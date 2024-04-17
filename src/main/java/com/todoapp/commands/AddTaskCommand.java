package com.todoapp.commands;

import com.todoapp.factories.SimpleTaskFactory;
import com.todoapp.managers.TaskManager;
import com.todoapp.models.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AddTaskCommand implements Command {
  private Scanner scanner;
  private SimpleTaskFactory factory;
  private TaskManager manager;

  public AddTaskCommand(Scanner scanner, SimpleTaskFactory factory, TaskManager manager) {
    this.scanner = scanner;
    this.factory = factory;
    this.manager = manager;
  }

  @Override
  public void execute() {
    try {
      System.out.print("Enter Description: ");
      String description = scanner.nextLine();
      if (description.isEmpty()) {
        throw new IllegalArgumentException("Description cannot be empty.");
      }

      System.out.print("Enter Deadline (yyyy-mm-dd): ");
      String date = scanner.nextLine();
      LocalDateTime deadline = LocalDateTime.parse(date + "T00:00:00");
      if(deadline.isBefore(LocalDateTime.now())){
        throw new IllegalArgumentException("Deadline cannot be before current Time.");

      }

      System.out.print("Enter Tags (comma-separated): ");
      String tagsStr = scanner.nextLine();
      Set<String> tags = new HashSet<>(Arrays.asList(tagsStr.split(",")));

      Task task = factory.createTask(description, deadline, tags);
      manager.addTask(task);
      System.out.println("Task added successfully!");
    } catch (DateTimeParseException e) {
      System.out.println("Error: Invalid date format. Please use yyyy-mm-dd.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
