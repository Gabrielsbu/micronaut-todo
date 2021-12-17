package br.com.estudo.domain.repositories.impl;

import br.com.estudo.domain.exceptions.GlobalException;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.repositories.TodoRepository;
import br.com.estudo.domain.repositories.TodoRepositoryJpa;
import br.com.estudo.domain.repositories.params.TodoParams;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final EntityManager entityManager;
    private final TodoRepositoryJpa todoRepositoryJpa;

    @Override
    @Transactional
    public Page<Todo> searchAllTodos(TodoParams params, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Todo> query = criteriaBuilder.createQuery(Todo.class);

        Root<Todo> root = query.from(Todo.class);

        Path<String> description = root.get("description");

        List<Predicate> predicates = new ArrayList<>();

        if(params.getDescription() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(description), "%" + params.getDescription().toLowerCase() + "%"));
        }

        if(!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Todo> typedQuery = this.entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getNumber() * pageable.getSize());
        typedQuery.setMaxResults(pageable.getSize());

        List<Todo> result = typedQuery.getResultList();

        return Page.of(result, pageable, result.size());
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepositoryJpa.save(todo);
    }

    @Override
    public Todo findById(Long id) {
        return todoRepositoryJpa.findById(id).orElseThrow(() -> new GlobalException("Todo not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteById(Long id) {
        todoRepositoryJpa.deleteById(id);
    }

    @Override
    public Todo update(Todo todo) {
        return todoRepositoryJpa.update(todo);
    }

}
