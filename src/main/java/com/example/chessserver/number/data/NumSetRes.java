package com.example.chessserver.number.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class NumSetRes {
    private final int sequence;
    private final Map<String, List<Integer>> nums;

    @Builder
    private NumSetRes(int sequence, Map<String, List<Integer>> nums) {
        this.sequence = sequence;
        this.nums = nums;
    }
}
