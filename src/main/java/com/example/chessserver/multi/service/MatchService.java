package com.example.chessserver.multi.service;

import com.example.chessserver.multi.domain.Game;
import com.example.chessserver.multi.data.GameDto;
import com.example.chessserver.multi.domain.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    private final Map<String, Game> games = new HashMap<>();
    private final Queue<Player> playerList = new LinkedList<>();

    public GameDto match(String playerId) {
        if (games.containsKey(playerId)) {
            return GameDto.builder()
                    .id(games.get(playerId).getId())
                    .build();
        }
        synchronized (playerList) {
            if (!containPlayer(playerId)) {
                playerList.add(new Player(playerId));
            }
            while (playerList.size() >= 2) {
                Player player1 = playerList.poll();
                Player player2 = playerList.poll();
                Game game = new Game(UUID.randomUUID().toString(), player1, player2);
                games.put(player1.getId(), game);
                games.put(player2.getId(), game);
            }
        }

        return GameDto.builder().build();
    }

    private boolean containPlayer(String playerId) {
        return playerList.stream().anyMatch(player -> player.getId().equals(playerId));
    }
}
