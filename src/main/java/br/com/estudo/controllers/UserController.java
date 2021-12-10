package br.com.estudo.controllers;

import br.com.estudo.domain.dtos.UpdateUserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.params.UserParams;
import br.com.estudo.domain.services.UserService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("/users")
public class UserController {

    @Inject
    private UserService userService;

    @Get
    public Page<User> getUsers(@RequestBean UserParams params, Pageable pageable){
        return userService.getAllUsers(params, pageable);
    }

    @Get("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @Post
    public User saveUser(User user){
        return userService.createUser(user);
    }

    @Put("/{id}")
    public User updateUser(@PathVariable Long id, UpdateUserDTO updateUserDTO){
        return userService.updateUser(id, updateUserDTO);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
}
