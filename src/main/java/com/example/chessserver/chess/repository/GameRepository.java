package com.example.chessserver.chess.repository;

import com.example.chessserver.chess.domain.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GameRepository {
    private final EntityManager em;

    public String saveGame(Game game) {
        em.persist(game);
        return game.getUuid();
    }

    public Optional<Game> findGameByUuid(String uuid) {
        String jpql = "SELECT g FROM Game g WHERE g.uuid = :uuid";
        return em.createQuery(jpql, Game.class)
                .setParameter("uuid", uuid)
                .getResultList().stream()
                .findFirst();
    }

    public void removeGame(String uuid) {
        if(findGameByUuid(uuid).isPresent()){
            em.remove(findGameByUuid(uuid).get());
        }
    }
    public void deleteAll() {
        String jpql = "SELECT g FROM Game g";
        List<Game> gameList = em.createQuery(jpql, Game.class).getResultList();
        gameList.forEach(em::remove);
    }
}
