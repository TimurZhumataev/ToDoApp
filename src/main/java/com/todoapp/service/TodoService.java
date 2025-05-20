package com.todoapp.service;

import com.todoapp.dto.TodoRequest;
import com.todoapp.dto.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(TodoRequest request);
    TodoResponse getTodoById(Long id);
    List<TodoResponse> getAllTodos();
    TodoResponse updateTodo(Long id, TodoRequest request);
    void deleteTodo(Long id);
}

