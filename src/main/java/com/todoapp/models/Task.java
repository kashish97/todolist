package com.todoapp.models;

import java.time.LocalDateTime;
import java.util.Set;

public class Task {
  private static int globalId = 1;
  private int id;
  private String description;
  private LocalDateTime deadline;
  private Set<String> tags;
  private boolean isCompleted;
  private LocalDateTime creationTime; // Timestamp when task was added
  private LocalDateTime completionTime; // Timestamp when task was completed

  public Task(String description, LocalDateTime deadline, Set<String> tags) {
    this.id = globalId++;
    this.description = description;
    this.deadline = deadline;
    this.tags = tags;
    this.isCompleted = false;
    this.creationTime = LocalDateTime.now(); // Set creation time on instantiation
    this.completionTime = null; // Initialize to null
  }

  // Getter and Setter for completion time
  public LocalDateTime getCompletionTime() {
    return completionTime;
  }

  public void setCompletionTime(LocalDateTime completionTime) {
    this.completionTime = completionTime;
    this.isCompleted = true; // Mark as completed when completion time is set
  }

  // Other getters and setters

  public static int getGlobalId() {
    return globalId;
  }

  public static void setGlobalId(int globalId) {
    Task.globalId = globalId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    this.isCompleted = completed;
    this.completionTime = LocalDateTime.now();
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Task ID: ").append(id)
        .append("\nDescription: ").append(description)
        .append("\nDeadline: ").append(deadline)
        .append("\nTags: ").append(String.join(", ", tags))
        .append("\nCompleted: ").append(isCompleted ? "Yes" : "No")
        .append("\nCreation Time: ").append(creationTime);
    if (isCompleted) {
      sb.append("\nCompletion Time: ").append(completionTime);
    }
    return sb.toString();
  }
}
