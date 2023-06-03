package com.example.chessserver.chess.domain;

import lombok.Getter;

@Getter
public class Player {
    private final String id;

    public Player(String id) {
        this.id = id;
    }
}

