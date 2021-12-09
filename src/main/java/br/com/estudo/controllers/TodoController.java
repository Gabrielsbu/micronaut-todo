package br.com.estudo.controllers;

import br.com.estudo.dtos.UpdateTodoDTO;
import br.com.estudo.models.Todo;
import br.com.estudo.repositories.TodoRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller("/todos")
@RequiredArgsConstructor
public class TodoController {


    private final TodoRepository todoRepository;

    @Get
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    @Post
    public HttpResponse<Todo> addTodo(Todo todo){
        return HttpResponse.created(todoRepository.save(todo));
    }

    @Get("/{id}")
    public Todo findOne(@PathVariable Long id){
        return todoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteById(@PathVariable Long id){
        Todo todo = findOne(id);

        todoRepository.deleteById(todo.getId());

        return HttpResponse.noContent();
    }

    @Put("/{id}")
    public Todo updateTodo(@PathVariable Long id, Todo todo){
        Todo todoExistent = todoRepository.findById(id).orElseThrow();

        todoExistent.setDescription(todo.getDescription());
        todoExistent.setDone(todo.getDone());

        return todoRepository.update(todoExistent);
    }

    @Patch("/{id}")
    public Todo updateStatusTodo(@PathVariable Long id, UpdateTodoDTO updateTodoDTO) {
        Todo todoExistent = todoRepository.findById(id).orElseThrow();
        todoExistent.setDone(updateTodoDTO.getDone());
        return todoRepository.update(todoExistent);
    }
}
