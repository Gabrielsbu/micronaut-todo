package br.com.estudo.domain.repositories;

import br.com.estudo.domain.models.Todo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TodoRepositoryJpa extends JpaRepository<Todo, Long> {
}
