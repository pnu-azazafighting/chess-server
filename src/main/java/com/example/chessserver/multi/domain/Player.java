package com.example.chessserver.multi.domain;

import lombok.Getter;

@Getter
public class Player {
    private final String id;

    public Player(String id) {
        this.id = id;
    }
}

