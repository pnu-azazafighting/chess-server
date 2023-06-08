package com.example.chessserver.chess.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MoveReqDto {
    private final String userId;
    private final List<Integer> start;
    private final List<Integer> end;
    @Builder
    private MoveReqDto(String userId, List<Integer> start, List<Integer> end) {
        this.userId = userId;
        this.start = start;
        this.end = end;
    }
}
