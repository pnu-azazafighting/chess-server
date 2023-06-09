package com.example.chessserver.chess.controller;

import com.example.chessserver.chess.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @DeleteMapping("test/data")
    public void deleteAllData() {
        testService.deleteAllData();
    }
}
