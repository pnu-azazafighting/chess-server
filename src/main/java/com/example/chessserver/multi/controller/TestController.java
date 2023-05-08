package com.example.chessserver.multi.controller;

import com.example.chessserver.multi.service.TestService;
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
