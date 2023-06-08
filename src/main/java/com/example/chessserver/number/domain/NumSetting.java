package com.example.chessserver.number.domain;

import com.example.chessserver.number.data.NumSetDto;

import java.util.List;
import java.util.Map;

public class NumSetting {
    private final Map<Integer, List<Integer>> nums;

    public NumSetting(Map<Integer, List<Integer>> nums) {
        this.nums = nums;
    }
    public NumSetDto toNumSetDto() {
        return NumSetDto.builder()
                .nums(nums)
                .build();
    }
}
