package br.com.estudo.domain.services.Impl;

import br.com.estudo.domain.dtos.todos.CreateTodoDTO;
import br.com.estudo.domain.dtos.todos.TodoDTO;
import br.com.estudo.domain.dtos.todos.UpdateTodoDTO;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.TodoRepository;
import br.com.estudo.domain.repositories.UserRepository;
import br.com.estudo.domain.repositories.params.TodoParams;
import br.com.estudo.domain.services.TodoService;
import br.com.estudo.domain.utils.converterDTO.todos.TodoConverterImpl;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Singleton
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoConverterImpl todoConverter = new TodoConverterImpl();
    private final UserRepository userRepository;

    @Override
    public Page<Todo> getAllTodos(TodoParams todoParams, Pageable pageable) {
        return todoRepository.searchAllTodos(todoParams, pageable);
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        return todoConverter.toDTO(todoRepository.findById(id));
    }

    @Override
    public Todo createTodo(CreateTodoDTO createTodoDTO) {
        User user = userRepository.findById(createTodoDTO.getUserId());

        Todo todo = new Todo();
        todo.setDescription(createTodoDTO.getDescription());
        todo.setDays(createTodoDTO.getDays());
        todo.setDateValidate(LocalDateTime.now().plusDays(createTodoDTO.getDays()));
        todo.setDone(false);
        todo.setUser(user);

        return todoRepository.save(todo);
    }


    @Override
    public HttpResponse<Void> deleteTodoById(Long id) {
        Todo todo = todoRepository.findById(id);

        todoRepository.deleteById(todo.getTodoId());

        return HttpResponse.noContent();
    }

    @Override
    public Todo alterStateTodo(Long id, UpdateTodoDTO updateTodoDTO) {
        Todo todoExistent = todoRepository.findById(id);
        todoExistent.setDone(updateTodoDTO.getDone());

        return todoRepository.update(todoExistent);
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Todo todoExistent = todoRepository.findById(id);
        todoExistent.setDescription(todo.getDescription());
        todoExistent.setDone(todo.getDone());

        return todoRepository.update(todoExistent);
    }
}
