package com.example.chessserver.chess.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MoveReqDto {
    private final String userId;
    private final String piece;
    private final List<Integer> start;
    private final List<Integer> end;
    @Builder
    private MoveReqDto(String userId, String piece, List<Integer> start, List<Integer> end) {
        this.userId = userId;
        this.piece = piece;
        this.start = start;
        this.end = end;
    }
}
