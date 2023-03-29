package com.example.chessserver.multi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private String id;
    private Player player1;
    private Player player2;

    public Game(String id, Player player1, Player player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
    }
}
