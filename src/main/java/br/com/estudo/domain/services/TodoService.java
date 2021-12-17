package br.com.estudo.domain.services;

import br.com.estudo.domain.dtos.todos.CreateTodoDTO;
import br.com.estudo.domain.dtos.todos.TodoDTO;
import br.com.estudo.domain.dtos.todos.UpdateTodoDTO;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.repositories.params.TodoParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;

public interface TodoService {
    Page<Todo> getAllTodos(TodoParams params, Pageable pageable);

    Todo createTodo(CreateTodoDTO todo);

    TodoDTO getTodoById(Long todoId);

    HttpResponse<Void> deleteTodoById(Long todoId);

    Todo alterStateTodo(Long todoId, UpdateTodoDTO updateTodoDTO);

    Todo updateTodo(Long todoId, Todo todo);
}
