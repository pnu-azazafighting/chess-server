package com.example.chessserver.chess.controller;

import com.example.chessserver.chess.data.*;
import com.example.chessserver.chess.service.ChessGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/multi")
@CrossOrigin
public class ChessGameController {
    private final ChessGameService chessGameService;

    @Operation(summary = "기물 세팅 정보 입력", description = "기물 세팅 정보 입력시에 서버에 저장하도록 요청하는 api")
    @PostMapping("/{gameId}/pieces")
    public ResponseEntity<Void> savePieceInfo(@PathVariable String gameId, @RequestBody PieceReqDto pieceReqDto) {
        chessGameService.savePieceSetting(gameId, pieceReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상대방 기물 세팅 정보 요청", description = "상대방 기물 세팅 정보를 서버로부터 요청하는 api")
    @ApiResponse(responseCode = "200", description = "상대방 기물 세팅 정보 조회 성공")
    @ApiResponse(responseCode = "204", description = "상대방 기물 세팅 정보 아직 입력되지 않음")
    @GetMapping("/{gameId}/pieces")
    public ResponseEntity<PieceResDto> responsePieceInfo(@PathVariable String gameId, @RequestParam String userId) {
        PieceResDto pieceResDto = chessGameService.getPieceSetting(gameId, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(pieceResDto);
    }

    @Operation(summary = "기물 이동 정보 입력", description = "기물 이동 정보 입력시에 서버에 저장하도록 요청하는 api")
    @PostMapping("/{gameId}/movements")
    public ResponseEntity<Void> savePieceMovement(@PathVariable String gameId, @RequestBody MoveReqDto moveReqDto) {
        chessGameService.saveMovement(gameId, moveReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상대방 이동 정보 요청", description = "상대방 기물 이동 정보를 서버로부터 요청하는 api")
    @ApiResponse(responseCode = "200", description = "상대방 기물 이동 정보 조회 성공")
    @ApiResponse(responseCode = "204", description = "상대방 기물 이동 정보 아직 입력되지 않음")
    @GetMapping("/{gameId}/movements")
    public ResponseEntity<MoveResDto> responsePieceMovement(@PathVariable String gameId, @RequestParam String userId) {
        MoveResDto moveResDto = chessGameService.getMovement(gameId, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(moveResDto);
    }

    @Operation(summary = "게임이 끝남을 서버에 알림", description = "게임이 끝나면 서버에서 방을 없앨 수 있도록 알리는 api")
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> gameOver(@PathVariable String gameId) {
        chessGameService.deleteGame(gameId);
        return ResponseEntity.ok().build();
    }
}
