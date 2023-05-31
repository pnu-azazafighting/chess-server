package com.example.chessserver.multi.exception;

public class NoGameException extends RuntimeException{
    public NoGameException() {
        super("게임이 매칭되지 않거나 종료되었습니다.");
    }
}
