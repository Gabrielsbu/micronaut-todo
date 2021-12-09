package br.com.estudo.controllers;

import br.com.estudo.models.Todo;
import br.com.estudo.repositories.params.TodoParams;
import br.com.estudo.services.TodoService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("/todos")
public class TodoController {

    @Inject
    private TodoService todoService;

    @Get
    public Page<Todo> getTodos(@RequestBean TodoParams params, Pageable pageable){
        return todoService.getAllTodos(params, pageable);
    }

    @Post
    public HttpResponse<Todo> addTodo(Todo todo){
        return HttpResponse.created(todoService.createTodo(todo));
    }

//    @Get("/{id}")
//    public Todo findOne(@PathVariable Long id){
//        return todoRepository.findById(id).orElseThrow(RuntimeException::new);
//    }
//
//    @Delete("/{id}")
//    public HttpResponse<Void> deleteById(@PathVariable Long id){
//        Todo todo = findOne(id);
//
//        todoRepository.deleteById(todo.getId());
//
//        return HttpResponse.noContent();
//    }
//
//    @Put("/{id}")
//    public Todo updateTodo(@PathVariable Long id, Todo todo){
//        Todo todoExistent = todoRepository.findById(id).orElseThrow();
//
//        todoExistent.setDescription(todo.getDescription());
//        todoExistent.setDone(todo.getDone());
//
//        return todoRepository.update(todoExistent);
//    }
//
//    @Patch("/{id}")
//    public Todo updateStatusTodo(@PathVariable Long id, UpdateTodoDTO updateTodoDTO) {
//        Todo todoExistent = todoRepository.findById(id).orElseThrow();
//        todoExistent.setDone(updateTodoDTO.getDone());
//        return todoRepository.update(todoExistent);
//    }
}
