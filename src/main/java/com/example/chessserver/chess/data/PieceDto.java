package com.example.chessserver.chess.data;

import com.example.chessserver.chess.domain.PieceSetting;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PieceDto {
    private final List<Integer> king;
    private final List<Integer> bishop;
    private final List<Integer> rook;
    private final List<Integer> pawn;
    private final List<Integer> trap;
    @Builder
    private PieceDto(List<Integer> king, List<Integer> bishop, List<Integer> rook, List<Integer> pawn, List<Integer> trap) {
        this.king = king;
        this.bishop = bishop;
        this.rook = rook;
        this.pawn = pawn;
        this.trap = trap;
    }

    public PieceSetting toPieceSetting() {
        return new PieceSetting(king, bishop, rook, pawn, trap);
    }
}
