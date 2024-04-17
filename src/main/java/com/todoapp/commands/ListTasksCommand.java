package com.todoapp.commands;

import com.todoapp.managers.TaskManager;
import com.todoapp.models.Task;

import java.util.*;

public class ListTasksCommand implements Command {
  private Scanner scanner;
  private TaskManager manager;

  public ListTasksCommand(Scanner scanner, TaskManager manager) {
    this.scanner = scanner;
    this.manager = manager;
  }

  @Override
  public void execute() {
    System.out.println("Filter tasks by tags (comma-separated, leave empty if no filter):");
    String input = scanner.nextLine();
    Set<String> tags = input.isEmpty() ? null : new HashSet<>(Arrays.asList(input.split(",")));

    System.out.println("Choose sort option (1- Deadline, 2- Description, leave empty for no sort):");
    String sortInput = scanner.nextLine();
    Comparator<Task> sortOption = null;
    if ("1".equals(sortInput)) {
      sortOption = Comparator.comparing(Task::getDeadline);
    } else if ("2".equals(sortInput)) {
      sortOption = Comparator.comparing(Task::getDescription);
    }

    List<Task> tasks = manager.listTasks(Optional.ofNullable(tags), Optional.ofNullable(sortOption));
    if (tasks.isEmpty()) {
      System.out.println("No tasks found.");
    } else {
      tasks.forEach(System.out::println);
    }
  }
}
