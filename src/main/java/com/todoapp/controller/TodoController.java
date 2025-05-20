package com.todoapp.controller;

import com.todoapp.dto.TodoRequest;
import com.todoapp.dto.TodoResponse;
import com.todoapp.service.TodoServiceImpl;
import com.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoServiceImpl todoServiceImpl;

    public TodoController(TodoService todoService, TodoServiceImpl todoServiceImpl) {
        this.todoService = todoService;
        this.todoServiceImpl = todoServiceImpl;
    }

    @PostMapping
    public TodoResponse create(@Valid @RequestBody TodoRequest request) {
        return todoService.createTodo(request);
    }

    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoResponse getById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
