package br.com.estudo.domain.repositories;

import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.params.TodoParams;
import br.com.estudo.domain.repositories.params.UserParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public interface UserRepository {

    Page<User> searchAllUsers(UserParams params, Pageable pageable);
    User save(@Valid @NotNull User user);
    User findById(Long id);
    void deleteById(Long id);
    User update(User user);

    User findByEmail(String email);
}
