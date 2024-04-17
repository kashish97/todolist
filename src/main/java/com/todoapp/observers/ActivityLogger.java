package com.todoapp.observers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActivityLogger implements EventListener {
  private List<LogEntry> logs = new ArrayList<>();

  @Override
  public void update(String eventType, String message) {
    logs.add(new LogEntry(LocalDateTime.now(), eventType + ": " + message));
    System.out.println("Logged: " + eventType + " - " + message);
  }

  public List<String> getLogs(Optional<LocalDateTime> start, Optional<LocalDateTime> end) {
    if (start.isPresent() && end.isPresent()) {
      LocalDateTime startTime = start.get();
      LocalDateTime endTime = end.get();
      return logs.stream()
          .filter(log -> log.getTime().isAfter(startTime) && log.getTime().isBefore(endTime))
          .map(LogEntry::getMessage)
          .collect(Collectors.toList());
    }
    return logs.stream()
        .map(LogEntry::getMessage)
        .collect(Collectors.toList());
  }

  private static class LogEntry {
    private LocalDateTime time;
    private String message;

    public LogEntry(LocalDateTime time, String message) {
      this.time = time;
      this.message = message;
    }

    public LocalDateTime getTime() {
      return time;
    }

    public String getMessage() {
      return message;
    }
  }
}
