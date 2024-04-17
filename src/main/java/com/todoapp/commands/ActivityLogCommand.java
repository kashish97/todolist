package com.todoapp.commands;

import com.todoapp.managers.TaskManager;
import com.todoapp.observers.ActivityLogger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ActivityLogCommand implements Command {
  private Scanner scanner;
  private ActivityLogger logger;

  public ActivityLogCommand(Scanner scanner, ActivityLogger logger) {
    this.scanner = scanner;
    this.logger = logger;
  }

  @Override
  public void execute() {
    System.out.print("Enter start date (yyyy-mm-dd, leave empty if no start date): ");
    String startDateStr = scanner.nextLine();
    System.out.print("Enter end date (yyyy-mm-dd, leave empty if no end date): ");
    String endDateStr = scanner.nextLine();

    Optional<LocalDateTime> start = startDateStr.isEmpty() ? Optional.empty() : Optional.of(LocalDateTime.parse(startDateStr + "T00:00:00"));
    Optional<LocalDateTime> end = endDateStr.isEmpty() ? Optional.empty() : Optional.of(LocalDateTime.parse(endDateStr + "T23:59:59"));

    List<String> logs = logger.getLogs(start, end);
    if (logs.isEmpty()) {
      System.out.println("No activity logs found for the specified period.");
    } else {
      logs.forEach(System.out::println);
    }
  }
}
