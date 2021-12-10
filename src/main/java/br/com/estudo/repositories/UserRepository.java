package br.com.estudo.repositories;

import br.com.estudo.models.User;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Executable
    User findByEmail(String email);
}
