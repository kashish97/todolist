package com.todoapp.commands;

import com.todoapp.managers.TaskManager;

import java.util.Scanner;

public class RemoveTaskCommand implements Command {
  private Scanner scanner;
  private TaskManager manager;

  public RemoveTaskCommand(Scanner scanner, TaskManager manager) {
    this.scanner = scanner;
    this.manager = manager;
  }

  @Override
  public void execute() {
    try {
      System.out.print("Enter Task ID to Remove: ");
      int id = Integer.parseInt(scanner.nextLine());
      if (manager.getTask(id) == null) {
        throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
      }
      manager.removeTask(id);
      System.out.println("Task removed successfully!");
    } catch (NumberFormatException e) {
      System.out.println("Error: Invalid ID format. Please enter a numerical ID.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
