package br.com.estudo.domain.services;

import br.com.estudo.domain.dtos.UpdateTodoDTO;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.repositories.params.TodoParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;

public interface TodoService {
    Page<Todo> getAllTodos(TodoParams params, Pageable pageable);

    Todo createTodo(Todo todo);

    Todo getTodoById(Long id);

    HttpResponse<Void> deleteTodoById(Long id);

    Todo alterStateTodo(Long id, UpdateTodoDTO updateTodoDTO);

    Todo updateTodo(Long id, Todo todo);
}
