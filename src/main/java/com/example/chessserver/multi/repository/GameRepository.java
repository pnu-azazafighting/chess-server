package com.example.chessserver.multi.repository;

import com.example.chessserver.multi.domain.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class GameRepository {
    private final EntityManager em;

    public String saveGame(Game game) {
        em.persist(game);
        return game.getUuid();
    }
}
