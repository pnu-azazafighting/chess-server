package com.example.chessserver.match.controller;

import com.example.chessserver.multi.data.GameDto;
import com.example.chessserver.match.service.MatchServiceRouter;
import com.example.chessserver.user.data.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matchs")
@CrossOrigin
public class MatchController {
    private final MatchServiceRouter matchServiceRouter;
    @Operation(summary = "유저 아이디 입력", description = "유저 아이디 입력시 퀵 매칭 후 게임 룸 아이디를 리턴하는 api")
    @ApiResponse(responseCode = "200", description = "게임 매칭 성공")
    @ApiResponse(responseCode = "204", description = "아직 매칭되지 않음")
    @PostMapping("chess")
    public ResponseEntity<GameDto> quickChessMatch(@RequestBody UserDto userDto) {
        GameDto gameDto = matchServiceRouter.getMatchServiceImpl("Chess").getGameUuid(userDto.getUserId());
        if(Objects.isNull(gameDto.getGameId())) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gameDto);
    }
    @Operation(summary = "숫자게임 유저 아이디 입력", description = "숫자게임 유저 아이디 입력시 퀵 매칭 후 게임 룸 아이디를 리턴하는 api")
    @ApiResponse(responseCode = "200", description = "게임 매칭 성공")
    @ApiResponse(responseCode = "204", description = "아직 매칭되지 않음")
    @PostMapping("/number")
    public ResponseEntity<GameDto> quickNumberMatch(@RequestBody UserDto userDto) {
        GameDto gameDto = matchServiceRouter.getMatchServiceImpl("Number").getGameUuid(userDto.getUserId());
        if(Objects.isNull(gameDto.getGameId())) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gameDto);
    }
}
