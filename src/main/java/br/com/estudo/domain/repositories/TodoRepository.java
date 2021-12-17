package br.com.estudo.domain.repositories;

import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.repositories.params.TodoParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public interface TodoRepository {
    Page<Todo> searchAllTodos(TodoParams params, Pageable pageable);

    Todo save(@Valid @NotNull Todo todo);

    Todo findById(Long todoId);

    void deleteById(Long todoId);

    Todo update(Todo todo);
}
