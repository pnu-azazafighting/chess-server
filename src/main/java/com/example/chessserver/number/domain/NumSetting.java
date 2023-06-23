package com.example.chessserver.number.domain;

import com.example.chessserver.number.data.NumSetDto;

import java.util.List;
import java.util.Map;

public class NumSetting {
    private final Map<String, List<Integer>> nums;

    public NumSetting(Map<String, List<Integer>> nums) {
        this.nums = nums;
    }

    public NumSetDto toNumSetDto() {
        return NumSetDto.builder()
                .nums(nums)
                .build();
    }
}
