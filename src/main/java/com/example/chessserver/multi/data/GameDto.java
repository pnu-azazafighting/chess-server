package com.example.chessserver.multi.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GameDto {
    private final String gameId;

    @Builder
    private GameDto(String id) {
        this.gameId = id;
    }
}
