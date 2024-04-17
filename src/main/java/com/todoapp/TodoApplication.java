package com.todoapp;

import com.todoapp.commands.ActivityLogCommand;
import com.todoapp.commands.AddTaskCommand;
import com.todoapp.commands.Command;
import com.todoapp.commands.CompleteTaskCommand;
import com.todoapp.commands.GetStatisticsCommand;
import com.todoapp.commands.ListTasksCommand;
import com.todoapp.commands.ModifyTaskCommand;
import com.todoapp.commands.RemoveTaskCommand;
import com.todoapp.factories.SimpleTaskFactory;
import com.todoapp.managers.TaskManager;
import com.todoapp.observers.ActivityLogger;
import com.todoapp.observers.EventManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TodoApplication {

  private static final Scanner scanner = new Scanner(System.in);
  private static final TaskManager manager = TaskManager.getInstance();
  private static final SimpleTaskFactory factory = new SimpleTaskFactory();
  private static final ActivityLogger logger = new ActivityLogger();
  private static final EventManager eventManager = manager.getEventManager();
  private static Map<Integer, Command> commands = new HashMap<>();

  public static void main(String[] args) {
    setupObservers();
    setupCommands();

    boolean running = true;
    while (running) {
      displayMenu();
      int choice = getUserChoice();
      if (choice == 5) {
        running = false;
        System.out.println("Exiting...");
      } else {
        executeCommand(choice);
      }
    }
  }

  private static void setupObservers() {
    eventManager.subscribe("add", logger);
    eventManager.subscribe("modify", logger);
    eventManager.subscribe("remove", logger);
  }

  private static void setupCommands() {
    commands.put(1, new AddTaskCommand(scanner, factory, manager));
    commands.put(2, new ModifyTaskCommand(scanner, manager));
    commands.put(3, new RemoveTaskCommand(scanner, manager));
    commands.put(4, new ListTasksCommand(scanner,manager));
    commands.put(6, new CompleteTaskCommand(scanner, manager)); // New command for completing tasks
    commands.put(7, new GetStatisticsCommand(scanner, manager)); // New command for completing tasks
    commands.put(8, new ActivityLogCommand(scanner, logger)); // New command for completing tasks

  }


  private static void displayMenu() {
    System.out.println("\nChoose an option:");
    System.out.println("1. Add Task");
    System.out.println("2. Modify Task");
    System.out.println("3. Remove Task");
    System.out.println("4. List Tasks");
    System.out.println("6. Complete Task"); // New menu option for completing tasks
    System.out.println("7. Get Statistics"); // New menu option for completing tasks
    System.out.println("8. Get Activity Logs"); // New menu option for completing tasks
    System.out.println("5. Exit");
    System.out.print("Your choice: ");
  }


  private static int getUserChoice() {
    while (!scanner.hasNextInt()) {
      scanner.next(); // consume the invalid input
      System.out.println("Invalid input. Please enter a number.");
      System.out.print("Your choice: ");
    }
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume the newline character
    return choice;
  }

  private static void executeCommand(int choice) {
    Command command = commands.get(choice);
    if (command != null) {
      command.execute();
    } else {
      System.out.println("Invalid choice. Please select a valid option.");
    }
  }
}
