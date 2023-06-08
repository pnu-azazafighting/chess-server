package com.example.chessserver.match.service;

import com.example.chessserver.chess.data.GameDto;
import com.example.chessserver.chess.domain.Game;
import com.example.chessserver.chess.exception.NoGameException;
import com.example.chessserver.chess.repository.GameRepository;
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
public class NumberMatchServiceImpl implements MatchService {
    private volatile Queue<String> numberPlayerList;
    private volatile Map<String, Game> numberGames;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public NumberMatchServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        numberGames = new HashMap<>();
        numberPlayerList = new LinkedList<>();
    }

    @Override
    public boolean isAvailable(String type) {
        return type.equals("Number");
    }

    @Override
    public GameDto getGameUuid(String playerUuid) {
        //TODO: 플레이어 유효성 확인 후 게임 찾아보기
        if (numberGames.containsKey(playerUuid)) {
            return GameDto.builder()
                    .gameId(numberGames.get(playerUuid).getUuid())
                    .build();
        }
        return GameDto.builder().build();
    }

    @Override
    public void insertWaitingList(String uuid) {
        if (!numberGames.containsKey(uuid) && !numberPlayerList.contains(uuid))
            numberPlayerList.add(uuid);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
    public void matchGame() {
        synchronized (numberPlayerList) {
            while (numberPlayerList.size() >= 2) {
                String uuid1 = numberPlayerList.poll();
                String uuid2 = numberPlayerList.poll();
                User player1 = userRepository.findByUuid(uuid1);
                User player2 = userRepository.findByUuid(uuid2);

                Game game = new Game(UUID.randomUUID().toString(), player1, player2);
                gameRepository.saveGame(game);

                numberGames.put(player1.getUuid(), game);
                numberGames.put(player2.getUuid(), game);
            }
        }
    }

    public void deleteGame(String uuid) {
        Game game = gameRepository.findGameByUuid(uuid).orElseThrow(NoGameException::new);
        numberGames.remove(game.getPlayer1().getUuid());
        numberGames.remove(game.getPlayer2().getUuid());
    }
}
