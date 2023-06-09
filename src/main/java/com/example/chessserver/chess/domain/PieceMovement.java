package com.example.chessserver.chess.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class PieceMovement {
    private final List<Integer> start;
    private final List<Integer> end;

    public PieceMovement(List<Integer> start, List<Integer> end) {
        this.start = start;
        this.end = end;
    }
}
