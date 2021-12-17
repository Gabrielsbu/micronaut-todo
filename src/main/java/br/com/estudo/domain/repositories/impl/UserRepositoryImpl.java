package br.com.estudo.domain.repositories.impl;

import br.com.estudo.domain.exceptions.GlobalException;
import br.com.estudo.domain.models.Todo;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.TodoRepository;
import br.com.estudo.domain.repositories.TodoRepositoryJpa;
import br.com.estudo.domain.repositories.UserRepository;
import br.com.estudo.domain.repositories.UserRepositoryJpa;
import br.com.estudo.domain.repositories.params.TodoParams;
import br.com.estudo.domain.repositories.params.UserParams;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;
    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepositoryJpa.findById(userId).orElseThrow(() -> new GlobalException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteById(Long userId) {
        userRepositoryJpa.deleteById(userId);
    }

    @Override
    public User update(User user) {
        return userRepositoryJpa.update(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepositoryJpa.findByEmail(email);
    }

    @Override
    @Transactional
    public Page<User> searchAllUsers(UserParams params, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        Path<String> email = root.get("email");

        List<Predicate> predicates = new ArrayList<>();

        if(params.getEmail() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(email), "%" + params.getEmail().toLowerCase() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<User> typedQuery = this.entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getNumber() * pageable.getSize());
        typedQuery.setMaxResults(pageable.getSize());

        List<User> result = typedQuery.getResultList();

        return Page.of(result, pageable, result.size());
    }

}
