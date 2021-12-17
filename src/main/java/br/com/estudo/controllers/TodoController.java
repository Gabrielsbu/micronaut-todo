package br.com.estudo.controllers;

import br.com.estudo.domain.dtos.todos.CreateTodoDTO;
import br.com.estudo.domain.dtos.todos.TodoDTO;
import br.com.estudo.domain.dtos.todos.UpdateTodoDTO;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.repositories.params.TodoParams;
import br.com.estudo.domain.services.TodoService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;
import static io.micronaut.security.rules.SecurityRule.*;

@Controller("/todos")
@Secured(IS_AUTHENTICATED)
public class TodoController {

    @Inject
    private TodoService todoService;

    @Get
    public Page<Todo> getTodos(@RequestBean TodoParams params, Pageable pageable){
        return todoService.getAllTodos(params, pageable);
    }

    @Post
    public HttpResponse<Todo> addTodo(@Body CreateTodoDTO todo){
        return HttpResponse.created(todoService.createTodo(todo));
    }

    @Get("/{id}")
    public TodoDTO findOne(@PathVariable Long id){
        return todoService.getTodoById(id);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteById(@PathVariable Long id){
        return todoService.deleteTodoById(id);
    }

    @Put("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Body Todo todo){
        return todoService.updateTodo(id, todo);
    }

    @Patch("/{id}")
    public Todo updateStatusTodo(@PathVariable Long id, @Body UpdateTodoDTO updateTodoDTO) {
        return todoService.alterStateTodo(id, updateTodoDTO);
    }
}
