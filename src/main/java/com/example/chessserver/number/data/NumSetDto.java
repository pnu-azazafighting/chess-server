package com.example.chessserver.number.data;

import com.example.chessserver.number.domain.NumSetting;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
@Getter
public class NumSetDto {
    private final String userId;
    private final Map<Integer, List<Integer>> nums;
    @Builder
    private NumSetDto(String userId, Map<Integer, List<Integer>> nums) {
        this.userId = userId;
        this.nums = nums;
    }
    public NumSetting toNumSetting() {
        return new NumSetting(nums);
    }
}
