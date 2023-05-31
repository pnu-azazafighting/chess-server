package com.example.chessserver.multi.controller;

import com.example.chessserver.multi.exception.NoContentException;
import com.example.chessserver.multi.exception.NoGameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> noContentExceptionHandler(NoContentException e) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoGameException.class)
    public ResponseEntity<String> noGameExceptionHandler(NoGameException e) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(e.toString());
    }
}
