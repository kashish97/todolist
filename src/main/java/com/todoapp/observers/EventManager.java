package com.todoapp.observers;

import java.util.*;

public class EventManager {
  private static EventManager instance;
  private Map<String, List<EventListener>> listeners = new HashMap<>();

  private EventManager() { }

  public static EventManager getInstance() {
    if (instance == null) {
      instance = new EventManager();
    }
    return instance;
  }
  public void subscribe(String eventType, EventListener listener) {
    this.listeners.putIfAbsent(eventType, new ArrayList<>());
    this.listeners.get(eventType).add(listener);
  }

  public void unsubscribe(String eventType, EventListener listener) {
    this.listeners.get(eventType).remove(listener);
  }

  public void notify(String eventType, String message) {
    List<EventListener> users = listeners.getOrDefault(eventType, new ArrayList<>());
    for (EventListener listener : users) {
      listener.update(eventType, message);
    }
  }
}

