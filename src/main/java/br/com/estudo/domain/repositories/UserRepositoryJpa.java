package br.com.estudo.domain.repositories;

import br.com.estudo.domain.models.User;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {

    @Executable
    User findByEmail(String email);
}
