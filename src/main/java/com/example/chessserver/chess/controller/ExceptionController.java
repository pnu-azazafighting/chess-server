package com.example.chessserver.chess.controller;

import com.example.chessserver.chess.exception.NoContentException;
import com.example.chessserver.chess.exception.NoGameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin(origins = "*")

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
