package br.com.estudo.repositories;

import br.com.estudo.models.Todo;
import br.com.estudo.repositories.params.TodoParams;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public interface TodoRepository {
    Page<Todo> searchAllTodos(TodoParams params, Pageable pageable);
    Todo save(@Valid @NotNull Todo todo);
}
