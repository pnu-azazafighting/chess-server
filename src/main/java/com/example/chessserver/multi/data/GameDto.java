package com.example.chessserver.multi.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GameDto {
    private final String uuid;

    @Builder
    private GameDto(String uuid) {
        this.uuid = uuid;
    }
}
