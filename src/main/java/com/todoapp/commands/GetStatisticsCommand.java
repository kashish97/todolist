package com.todoapp.commands;


import com.todoapp.managers.TaskManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class GetStatisticsCommand implements Command {

  private Scanner scanner;
  private TaskManager manager;

  public GetStatisticsCommand(Scanner scanner, TaskManager manager) {
    this.scanner = scanner;
    this.manager = manager;
  }

  @Override
  public void execute() {
    System.out.print("Enter start date (yyyy-mm-dd): ");
    String startDateStr = scanner.nextLine();
    System.out.print("Enter end date (yyyy-mm-dd): ");
    String endDateStr = scanner.nextLine();

    LocalDateTime start = LocalDateTime.parse(startDateStr + "T00:00:00");
    LocalDateTime end = LocalDateTime.parse(endDateStr + "T23:59:59");

    Map<String, Integer> stats = manager.getStatistics(start, end);
    System.out.println("Statistics from " + start + " to " + end + ":");
    System.out.println("Tasks Added: " + stats.get("Added"));
    System.out.println("Tasks Completed: " + stats.get("Completed"));
    System.out.println("Tasks Overdue: " + stats.get("Overdue"));
  }
}
