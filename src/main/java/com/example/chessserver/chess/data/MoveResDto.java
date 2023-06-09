package com.example.chessserver.chess.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MoveResDto {
    private final List<Integer> start;
    private final List<Integer> end;

    @Builder
    private MoveResDto(List<Integer> start, List<Integer> end) {
        this.start = start;
        this.end = end;
    }
}
