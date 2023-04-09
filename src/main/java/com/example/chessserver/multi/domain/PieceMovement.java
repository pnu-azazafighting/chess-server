package com.example.chessserver.multi.domain;

import lombok.Getter;

import java.util.List;
@Getter
public class PieceMovement {
    private final Piece piece;
    private final List<Integer> start;
    private final List<Integer> end;

    public PieceMovement(Piece piece, List<Integer> start, List<Integer> end) {
        this.piece = piece;
        this.start = start;
        this.end = end;
    }
}
