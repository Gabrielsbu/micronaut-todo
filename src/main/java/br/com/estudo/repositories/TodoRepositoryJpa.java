package br.com.estudo.repositories;

import br.com.estudo.models.Todo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TodoRepositoryJpa extends JpaRepository<Todo, Long> {
}
