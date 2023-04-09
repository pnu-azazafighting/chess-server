package com.example.chessserver.user.data;

import lombok.Getter;

@Getter
public class UserDto {
    private String userId;
    public UserDto() {

    }

    public UserDto(String userId) {
        this.userId = userId;
    }
}
