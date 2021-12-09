package br.com.estudo.services;

import br.com.estudo.models.Todo;
import br.com.estudo.repositories.params.TodoParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

public interface TodoService {
    Page<Todo> getAllTodos(TodoParams params, Pageable pageable);
    Todo createTodo(Todo todo);
}
