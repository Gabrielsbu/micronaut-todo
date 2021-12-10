package br.com.estudo.domain.services.Impl;

import br.com.estudo.domain.dtos.UpdateTodoDTO;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.repositories.TodoRepository;
import br.com.estudo.domain.repositories.params.TodoParams;
import br.com.estudo.domain.services.TodoService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public Page<Todo> getAllTodos(TodoParams todoParams, Pageable pageable) {
        return todoRepository.searchAllTodos(todoParams, pageable);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public HttpResponse<Void> deleteTodoById(Long id) {
        Todo todo = getTodoById(id);

        todoRepository.deleteById(todo.getId());

        return HttpResponse.noContent();
    }

    @Override
    public Todo alterStateTodo(Long id, UpdateTodoDTO updateTodoDTO) {
        Todo todoExistent = getTodoById(id);
        todoExistent.setDone(updateTodoDTO.getDone());

        return todoRepository.update(todoExistent);
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Todo todoExistent = getTodoById(id);
        todoExistent.setDescription(todo.getDescription());
        todoExistent.setDone(todo.getDone());

        return todoRepository.update(todoExistent);
    }
}
