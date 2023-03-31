package com.example.chessserver.multi.service;

import com.example.chessserver.multi.domain.Game;
import com.example.chessserver.multi.data.GameDto;
import com.example.chessserver.multi.repository.GameRepository;
import com.example.chessserver.user.User;
import com.example.chessserver.user.UserRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@EnableScheduling
@Transactional
@Service
public class MatchService {
    private volatile Map<String, Game> games;
    private volatile Queue<String> playerList;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public MatchService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        games = new HashMap<>();
        playerList = new LinkedList<>();
    }

    public GameDto getGameUuid(String playerUuid) {
        if (games.containsKey(playerUuid)) {
            return GameDto.builder()
                    .uuid(games.get(playerUuid).getUuid())
                    .build();
        }
        return GameDto.builder().build();
    }

    public void insertWaitingList(String uuid) {
        if (!games.containsKey(uuid) && !playerList.contains(uuid))
            playerList.add(uuid);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
    public void matchGame() {
        synchronized (playerList) {
            while (playerList.size() >= 2) {
                String uuid1 = playerList.poll();
                String uuid2 = playerList.poll();
                User player1 = userRepository.findByUuid(uuid1);
                User player2 = userRepository.findByUuid(uuid2);

                Game game = new Game(UUID.randomUUID().toString(), player1, player2);
                gameRepository.saveGame(game);

                games.put(player1.getUuid(), game);
                games.put(player2.getUuid(), game);
            }
        }
    }
}
