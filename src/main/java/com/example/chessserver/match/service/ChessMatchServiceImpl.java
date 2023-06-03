package com.example.chessserver.match.service;

import com.example.chessserver.multi.domain.Game;
import com.example.chessserver.multi.data.GameDto;
import com.example.chessserver.multi.exception.NoGameException;
import com.example.chessserver.multi.repository.GameRepository;
import com.example.chessserver.user.domain.User;
import com.example.chessserver.user.repository.UserRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@EnableScheduling
@Transactional
@Service
public class ChessMatchServiceImpl implements MatchService{
    private volatile Map<String, Game> chessGames;

    private volatile Queue<String> chessPlayerList;

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public ChessMatchServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        chessGames = new HashMap<>();
        chessPlayerList = new LinkedList<>();
    }

    @Override
    public boolean isAvailable(String type) {
        return type.equals("Chess");
    }

    public GameDto getGameUuid(String playerUuid) {
        if (chessGames.containsKey(playerUuid)) {
            return GameDto.builder()
                    .gameId(chessGames.get(playerUuid).getUuid())
                    .build();
        }
        return GameDto.builder().build();
    }
    public void insertWaitingList(String uuid) {
        if (!chessGames.containsKey(uuid) && !chessPlayerList.contains(uuid))
            chessPlayerList.add(uuid);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
    public void matchGame() {
        synchronized (chessPlayerList) {
            while (chessPlayerList.size() >= 2) {
                String uuid1 = chessPlayerList.poll();
                String uuid2 = chessPlayerList.poll();
                User player1 = userRepository.findByUuid(uuid1);
                User player2 = userRepository.findByUuid(uuid2);

                Game game = new Game(UUID.randomUUID().toString(), player1, player2);
                gameRepository.saveGame(game);

                chessGames.put(player1.getUuid(), game);
                chessGames.put(player2.getUuid(), game);
            }
        }
    }

    public void deleteGame(String uuid) {
        Game game = gameRepository.findGameByUuid(uuid).orElseThrow(NoGameException::new);
        chessGames.remove(game.getPlayer1().getUuid());
        chessGames.remove(game.getPlayer2().getUuid());
    }
}