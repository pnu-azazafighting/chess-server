package com.example.chessserver.multi.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PieceResDto {
    private final PieceDto piece;
    private final Integer sequence;
    @Builder
    private PieceResDto(PieceDto piece, Integer sequence) {
        this.piece = piece;
        this.sequence = sequence;
    }
}
