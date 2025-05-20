package com.todoapp.service;

import com.todoapp.ResourceNotFoundException;
import com.todoapp.TodoMapper;
import com.todoapp.dto.TodoRequest;
import com.todoapp.dto.TodoResponse;
import com.todoapp.model.Todo;
import com.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponse createTodo(TodoRequest request) {
        Todo todo = TodoMapper.toEntity(request);
        Todo saved = todoRepository.save(todo);
        return TodoMapper.toDto(saved);
    }

    @Override
    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        return TodoMapper.toDto(todo);
    }

    @Override
    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());

        Todo updated = todoRepository.save(todo);
        return TodoMapper.toDto(updated);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todoRepository.delete(todo);
    }
}

