package com.example.chessserver.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private final String uuid;
    @Builder
    private UserDto(String uuid) {
        this.uuid = uuid;
    }
}
