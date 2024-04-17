package com.todoapp.observers;

interface EventListener {

  void update(String eventType, String message);
}
