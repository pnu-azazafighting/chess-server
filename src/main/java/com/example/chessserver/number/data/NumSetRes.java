package com.example.chessserver.number.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class NumSetRes {
    private final int sequence;
    private final Map<Integer, List<Integer>> nums;

    @Builder
    private NumSetRes(int sequence, Map<Integer, List<Integer>> nums) {
        this.sequence = sequence;
        this.nums = nums;
    }
}
