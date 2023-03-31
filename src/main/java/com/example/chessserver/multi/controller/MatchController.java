package com.example.chessserver.multi.controller;

import com.example.chessserver.multi.data.GameDto;
import com.example.chessserver.multi.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matchs")
public class MatchController {
    private final MatchService matchService;
    @Operation(summary = "닉네임 입력", description = "닉네임 입력시 퀵 매칭 후 룸 번호를 리턴하는 api")
    @ApiResponse(responseCode = "200", description = "게임 매칭 성공")
    @ApiResponse(responseCode = "202", description = "아직 매칭되지 않음")
    @PostMapping
    public ResponseEntity<GameDto> quickMatch(@RequestBody String userUuid) {
        GameDto gameDto = matchService.getGameUuid(userUuid);
        if(Objects.isNull(gameDto.getUuid())) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gameDto);
    }
}
