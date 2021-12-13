package br.com.estudo.domain.services;

import br.com.estudo.domain.dtos.UpdateUserDTO;
import br.com.estudo.domain.dtos.UserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.params.UserParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;

public interface UserService {
    Page<User> getAllUsers(UserParams params, Pageable pageable);

    User createUser(User user);

    UserDTO getUserById(Long id);

    HttpResponse<Void> deleteUserById(Long id);

    User updateUser(Long id, UpdateUserDTO updateUserDTO);
}
