package com.example.chessserver.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private final String userId;
    @Builder
    private UserDto(String userId) {
        this.userId = userId;
    }
}
