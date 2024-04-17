package com.todoapp.commands;

import com.todoapp.managers.TaskManager;
import com.todoapp.models.Task;

import java.util.Scanner;

public class ModifyTaskCommand implements Command {
  private Scanner scanner;
  private TaskManager manager;

  public ModifyTaskCommand(Scanner scanner, TaskManager manager) {
    this.scanner = scanner;
    this.manager = manager;
  }

  @Override
  public void execute() {
    try {
      System.out.print("Enter Task ID to Modify: ");
      int id = Integer.parseInt(scanner.nextLine());
      Task task = manager.getTask(id);
      if (task == null) {
        throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
      }

      System.out.print("New Description: ");
      String description = scanner.nextLine();
      if (description.isEmpty()) {
        throw new IllegalArgumentException("Description cannot be empty.");
      }
      task.setDescription(description);
      manager.modifyTask(task);
      System.out.println("Task modified successfully!");
    } catch (NumberFormatException e) {
      System.out.println("Error: Invalid ID format. Please enter a numerical ID.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
