package br.com.estudo.domain.services;

import br.com.estudo.domain.dtos.users.CreateUserDTO;
import br.com.estudo.domain.dtos.users.UpdateUserDTO;
import br.com.estudo.domain.dtos.users.UserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.params.UserParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;

public interface UserService {
    Page<UserDTO> getAllUsers(UserParams params, Pageable pageable);

    User createUser(CreateUserDTO user);

    UserDTO getUserById(Long userId);

    HttpResponse<Void> deleteUserById(Long userId);

    User updateUser(Long userId, UpdateUserDTO updateUserDTO);
}
