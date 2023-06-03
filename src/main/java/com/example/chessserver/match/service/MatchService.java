package com.example.chessserver.match.service;

import com.example.chessserver.multi.data.GameDto;

public interface MatchService {
    boolean isAvailable(String type);
    GameDto getGameUuid(String playerUuid);

    void insertWaitingList(String uuid);

    void matchGame();

    void deleteGame(String uuid);
}
