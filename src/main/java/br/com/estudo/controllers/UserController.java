package br.com.estudo.controllers;

import br.com.estudo.domain.dtos.users.CreateUserDTO;
import br.com.estudo.domain.dtos.users.UpdateUserDTO;
import br.com.estudo.domain.dtos.users.UserDTO;
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
    public Page<UserDTO> getUsers(@RequestBean UserParams params, Pageable pageable){
        return userService.getAllUsers(params, pageable);
    }

    @Get("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @Post
    @Secured("isAnonymous()")
    public User saveUser(@Body CreateUserDTO user){
        return userService.createUser(user);
    }

    @Put("/{userId}")
    public User updateUser(@PathVariable Long userId, @Body UpdateUserDTO updateUserDTO){
        return userService.updateUser(userId, updateUserDTO);
    }

    @Delete("/{userId}")
    public HttpResponse<Void> deleteUserById(@PathVariable Long userId){
        return userService.deleteUserById(userId);
    }
}
