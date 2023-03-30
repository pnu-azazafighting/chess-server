package com.example.chessserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findByUuid(String uuid) {
        String jpql = "SELECT u FROM User u WHERE u.uuid = :uuid";
        return em.createQuery(jpql, User.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }
}
