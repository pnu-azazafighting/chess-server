package com.example.chessserver.user.data;

import lombok.*;

@Getter
public class NickNameDto {
    private String name;

    public NickNameDto() {
    }

    public NickNameDto(String name) {
        this.name = name;
    }
}
