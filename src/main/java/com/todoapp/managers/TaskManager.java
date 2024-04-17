package com.todoapp.managers;

import com.todoapp.models.Task;
import com.todoapp.observers.EventManager;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskManager {
  private static TaskManager instance;
  private Map<Integer, Task> tasks = new HashMap<>();
  private EventManager eventManager = EventManager.getInstance();  // Ensure EventManager is instantiated

  private TaskManager() { }

  public static TaskManager getInstance() {
    if (instance == null) {
      instance = new TaskManager();
    }
    return instance;
  }

  // This method returns the EventManager instance used by TaskManager
  public EventManager getEventManager() {
    return eventManager;
  }

  public void addTask(Task task) {
    tasks.put(task.getId(), task);
    eventManager.notify("add", "Added Task: " + task);
  }

  public Task getTask(int taskId) {
    return tasks.get(taskId);
  }

  // Existing methods, ensuring that modifyTask really updates the task in the map
  public void modifyTask(Task task) {
    if (tasks.containsKey(task.getId())) {
      tasks.put(task.getId(), task); // Replace the old task with the updated task
      System.out.println("Task updated: " + task);
    } else {
      System.out.println("Task not found with ID: " + task.getId());
    }
  }


  public void removeTask(int taskId) {
    tasks.remove(taskId);
    eventManager.notify("remove", "Removed Task ID: " + taskId);
  }

  public List<Task> listTasks(Optional<Set<String>> filterTags, Optional<Comparator<Task>> sortCriterion) {
    Stream<Task> taskStream = tasks.values().stream();
    if (filterTags.isPresent()) {
      Set<String> tags = filterTags.get();
      taskStream = taskStream.filter(task -> task.getTags().containsAll(tags));
    }
    if (sortCriterion.isPresent()) {
      taskStream = taskStream.sorted(sortCriterion.get());
    }
    return taskStream.collect(Collectors.toList());
  }

  public Map<String, Integer> getStatistics(LocalDateTime start, LocalDateTime end) {
    Map<String, Integer> stats = new HashMap<>();
    int added = 0, completed = 0, overdue = 0;

    for (Task task : tasks.values()) {
      if (task.getCreationTime().isEqual(start) || task.getCreationTime().isAfter(start) && task.getCreationTime().isBefore(end)) {
        added++;
      }
      if (task.isCompleted() && task.getCompletionTime() != null && task.getCompletionTime().isAfter(start) && task.getCompletionTime().isBefore(end)) {
        completed++;
      }
      if (!task.isCompleted() && task.getDeadline().isBefore(LocalDateTime.now()) && task.getDeadline().isAfter(start) && task.getDeadline().isBefore(end)) {
        overdue++;
      }
    }

    stats.put("Added", added);
    stats.put("Completed", completed);
    stats.put("Overdue", overdue);
    return stats;
  }


  public void clearTasks() {
    tasks.clear();
  }
}
