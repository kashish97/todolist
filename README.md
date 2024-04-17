TODO Task Tracking Application Documentation
Overview
The TODO Task Tracking Application is designed to help users manage their tasks efficiently without the need for external databases. It allows for the addition, modification, removal, and viewing of tasks, along with the ability to mark tasks as completed. The application also provides statistical data about task completion and can generate activity logs for specified time periods.

Features
Task Management

Add new tasks with descriptions, deadlines, and tags.
Modify existing tasks.
Remove tasks from the system.
Mark tasks as completed, automatically recording the completion time.
Task Querying

List all tasks with optional filtering by tags and sorting by attributes such as deadline or description.
Statistics

Generate statistics for tasks added, completed, or overdue within a specific time period.
Activity Logging

Maintain a log of all activities (additions, modifications, removals) with optional retrieval based on time filters.
Installation
No installation is necessary for running the application directly from a development environment like an IDE or through a Java runtime by executing the compiled .jar file.

Usage Instructions
Starting the Application

Run the Main class in your IDE or execute the jar file via the command line:
bash
Copy code
java -jar todoapp.jar
Navigating the Menu

Use the console-based menu to choose actions by entering the number corresponding to each action:
markdown
Copy code
1. Add Task
2. Modify Task
3. Remove Task
4. List Tasks
5. Complete Task
6. Get Statistics
7. Get Activity Log
8. Exit
Adding a Task

Enter the task details as prompted, including the description, deadline (in YYYY-MM-DD format), and tags (comma-separated).
Modifying a Task

Provide the task ID and the new details to update the task.
Completing a Task

Enter the task ID for the task you wish to mark as completed.
Generating Statistics

Enter the start and end dates for the period you want to analyze.
Retrieving Activity Logs

Specify the time period for which the logs should be retrieved, if desired.
Developer Guide
Architecture

The application follows an MVC-like structure:
Model: Task class
View: Console-based user interface
Controller: TaskManager handles task operations.
Adding New Features

Extend the Command interface for new actions.
Implement additional methods in TaskManager for new data manipulations.
Testing

Unit tests are located under src/test/java. Extend these to cover new functionality.
Dependencies

JUnit for testing.
No external databases or frameworks are required.
