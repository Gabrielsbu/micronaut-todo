package br.com.estudo.controllers;

import br.com.estudo.domain.dtos.UpdateUserDTO;
import br.com.estudo.domain.dtos.UserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.params.UserParams;
import br.com.estudo.domain.services.UserService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;
import static io.micronaut.security.rules.SecurityRule.*;


@Controller("/users")
@Secured(IS_AUTHENTICATED)
public class UserController {

    @Inject
    private UserService userService;

    @Get
    public Page<User> getUsers(@RequestBean UserParams params, Pageable pageable){
        return userService.getAllUsers(params, pageable);
    }

    @Get("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @Post
    @Secured("isAnonymous()")//TODO: Criar um UsarDTO para não precisar enviar o createdAt, updatedAt e ID
    public User saveUser(@Body User user){
        return userService.createUser(user);
    }

    @Put("/{id}")
    public User updateUser(@PathVariable Long id, @Body UpdateUserDTO updateUserDTO){
        return userService.updateUser(id, updateUserDTO);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteUserById(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
}
