package com.example.chessserver.chess.domain;

import com.example.chessserver.chess.data.PieceDto;

import java.util.List;

public class PieceSetting {
    private final List<Integer> king;
    private final List<Integer> bishop;
    private final List<Integer> rook;
    private final List<Integer> pawn;
    private final List<Integer> trap;

    public PieceSetting(List<Integer> king, List<Integer> bishop, List<Integer> rook, List<Integer> pawn, List<Integer> trap) {
        this.king = king;
        this.bishop = bishop;
        this.rook = rook;
        this.pawn = pawn;
        this.trap = trap;
    }

    public PieceDto toPieceDto() {
        return PieceDto.builder()
                .king(king)
                .bishop(bishop)
                .pawn(pawn)
                .rook(rook)
                .trap(trap).build();
    }
}
