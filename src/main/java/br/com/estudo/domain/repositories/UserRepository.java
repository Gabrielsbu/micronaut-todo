package br.com.estudo.domain.repositories;

import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.params.UserParams;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

@Singleton
public interface UserRepository {

    Page<User> searchAllUsers(UserParams params, Pageable pageable);
    User save(User user);
    User findById(Long userId);
    void deleteById(Long userId);
    User update(User user);

    User findByEmail(String email);
}
