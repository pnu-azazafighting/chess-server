package com.example.chessserver.number.controller;

import com.example.chessserver.chess.data.MoveReqDto;
import com.example.chessserver.chess.data.MoveResDto;
import com.example.chessserver.number.data.NumSetDto;
import com.example.chessserver.number.data.NumSetRes;
import com.example.chessserver.number.service.NumberGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/number")
@CrossOrigin(origins = "*")
public class NumberGameController {
    private final NumberGameService numberGameService;

    @Operation(summary = "숫자게임 기물 세팅 정보 입력", description = "기물 세팅 정보 입력시에 서버에 저장하도록 요청하는 api")
    @PostMapping("/{gameId}/pieces")
    public ResponseEntity<Void> savePieceInfo(@PathVariable String gameId, @RequestBody NumSetDto numSetDto) {
        numberGameService.savePieceSetting(gameId, numSetDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "숫자 게임 상대방 기물 세팅 정보 요청", description = "상대방 기물 세팅 정보를 서버로부터 요청하는 api")
    @ApiResponse(responseCode = "200", description = "상대방 기물 세팅 정보 조회 성공")
    @ApiResponse(responseCode = "204", description = "상대방 기물 세팅 정보 아직 입력되지 않음")
    @GetMapping("/{gameId}/pieces")
    public ResponseEntity<NumSetRes> responsePieceInfo(@PathVariable String gameId, @RequestParam String userId) {
        NumSetRes numSetResDto = numberGameService.getPieceSetting(gameId, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(numSetResDto);
    }

    @Operation(summary = "숫자 게임 기물 이동 정보 입력", description = "기물 이동 정보 입력시에 서버에 저장하도록 요청하는 api")
    @PostMapping("/{gameId}/movements")
    public ResponseEntity<Void> savePieceMovement(@PathVariable String gameId, @RequestBody MoveReqDto moveReqDto) {
        numberGameService.saveMovement(gameId, moveReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "숫자 게임 상대방 이동 정보 요청", description = "상대방 기물 이동 정보를 서버로부터 요청하는 api")
    @ApiResponse(responseCode = "200", description = "상대방 기물 이동 정보 조회 성공")
    @ApiResponse(responseCode = "204", description = "상대방 기물 이동 정보 아직 입력되지 않음")
    @GetMapping("/{gameId}/movements")
    public ResponseEntity<MoveResDto> responsePieceMovement(@PathVariable String gameId, @RequestParam String userId) {
        MoveResDto moveResDto = numberGameService.getMovement(gameId, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(moveResDto);
    }

    @Operation(summary = "숫자 게임 게임이 끝남을 서버에 알림", description = "게임이 끝나면 서버에서 방을 없앨 수 있도록 알리는 api")
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> gameOver(@PathVariable String gameId) {
        numberGameService.deleteGame(gameId, "Number");
        return ResponseEntity.ok().build();
    }
}
