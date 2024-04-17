package com.todoapp.commands;

import com.todoapp.managers.TaskManager;
import com.todoapp.models.Task;

import java.util.Scanner;

public class CompleteTaskCommand implements Command {
  private Scanner scanner;
  private TaskManager manager;

  public CompleteTaskCommand(Scanner scanner, TaskManager manager) {
    this.scanner = scanner;
    this.manager = manager;
  }

  @Override
  public void execute() {
    System.out.print("Enter Task ID to Complete: ");
    int id = Integer.parseInt(scanner.nextLine());
    Task task = manager.getTask(id);
    if (task != null) {
      task.setCompleted(true);
      manager.modifyTask(task); // Ensure the manager knows the task has been updated
      System.out.println("Task marked as completed successfully!");
    } else {
      System.out.println("Task not found.");
    }
  }
}
