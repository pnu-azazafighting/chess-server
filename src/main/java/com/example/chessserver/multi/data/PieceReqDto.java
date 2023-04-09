package com.example.chessserver.multi.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PieceReqDto {
    private final String userId;
    private final PieceDto piece;
    @Builder
    private PieceReqDto(String userId, PieceDto piece) {
        this.userId = userId;
        this.piece = piece;
    }
}
