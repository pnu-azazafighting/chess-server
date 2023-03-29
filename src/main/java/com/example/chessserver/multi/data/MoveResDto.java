package com.example.chessserver.multi.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
public class MoveResDto {
    private final String piece;
    private final List<Integer> start;
    private final List<Integer> end;
    @Builder
    private MoveResDto(String piece, List<Integer> start, List<Integer> end) {
        this.piece = piece;
        this.start = start;
        this.end = end;
    }
}
