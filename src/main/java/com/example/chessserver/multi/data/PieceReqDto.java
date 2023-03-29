package com.example.chessserver.multi.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PieceReqDto {
    private final String gameId;
    private final String userId;
    private final PieceDto piece;
    @Builder
    private PieceReqDto(String gameId, String userId, PieceDto piece) {
        this.gameId = gameId;
        this.userId = userId;
        this.piece = piece;
    }
}
