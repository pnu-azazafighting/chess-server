package com.example.chessserver.multi.controller;

import com.example.chessserver.multi.data.MoveReqDto;
import com.example.chessserver.multi.data.MoveResDto;
import com.example.chessserver.multi.data.PieceReqDto;
import com.example.chessserver.multi.data.PieceResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/multi")
public class MultiGameController {
    @Operation(summary = "기물 세팅 정보 입력", description = "기물 세팅 정보 입력시에 서버에 저장하도록 요청하는 api")
    @PostMapping("/pieces")
    public ResponseEntity<Void> getPieceInfo(@RequestBody PieceReqDto pieceReqDto) {
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "상대방 기물 세팅 정보 요청", description = "상대방 기물 세팅 정보를 서버로부터 요청하는 api")
    @ApiResponse(responseCode = "200", description = "상대방 기물 세팅 정보 조회 성공")
    @ApiResponse(responseCode = "202", description = "상대방 기물 세팅 정보 아직 입력되지 않음")
    @GetMapping("/pieces")
    public ResponseEntity<PieceResDto> responsePieceInfo(@RequestParam String gameId, @RequestParam String userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(PieceResDto.builder().build());
    }
    @Operation(summary = "기물 이동 정보 입력", description = "기물 이동 정보 입력시에 서버에 저장하도록 요청하는 api")
    @PostMapping("/games/{gameId}")
    public ResponseEntity<Void> movePiece(@PathVariable String gameId, @RequestBody MoveReqDto moveReqDto) {
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "상대방 이동 정보 요청", description = "상대방 기물 이동 정보를 서버로부터 요청하는 api")
    @ApiResponse(responseCode = "200", description = "상대방 기물 이동 정보 조회 성공")
    @ApiResponse(responseCode = "202", description = "상대방 기물 이동 정보 아직 입력되지 않음")
    @GetMapping("/games/{gameId}")
    public ResponseEntity<MoveResDto> movePiece(@PathVariable String gameId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(MoveResDto.builder().build());
    }
    @Operation(summary = "게임이 끝남을 서버에 알림", description = "게임이 끝나면 서버에서 방을 없앨 수 있도록 알리는 api")
    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<Void> gameOver(@PathVariable String gameId) {
        return ResponseEntity.ok().build();
    }
}
