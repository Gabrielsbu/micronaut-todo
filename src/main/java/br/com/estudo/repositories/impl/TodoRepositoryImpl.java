package br.com.estudo.repositories.impl;

import br.com.estudo.models.Todo;
import br.com.estudo.repositories.TodoRepository;
import br.com.estudo.repositories.TodoRepositoryJpa;
import br.com.estudo.repositories.params.TodoParams;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
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

}
