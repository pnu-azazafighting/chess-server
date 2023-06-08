package com.example.chessserver.user.repository;

import com.example.chessserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public void deleteAll() {
        String jpql = "SELECT u FROM User u";
        List<User> users = em.createQuery(jpql, User.class).getResultList();
        users.forEach(em::remove);
    }
}
