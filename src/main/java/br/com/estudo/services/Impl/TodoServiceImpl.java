package br.com.estudo.services.Impl;

import br.com.estudo.models.Todo;
import br.com.estudo.repositories.TodoRepositoryJpa;
import br.com.estudo.repositories.TodoRepository;
import br.com.estudo.repositories.impl.TodoRepositoryImpl;
import br.com.estudo.repositories.params.TodoParams;
import br.com.estudo.services.TodoService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
//    private final TodoRepositoryImpl todoRepository;

    @Override
    public Page<Todo> getAllTodos(TodoParams todoParams, Pageable pageable) {
        return todoRepository.searchAllTodos(todoParams, pageable);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
