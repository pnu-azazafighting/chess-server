package com.example.chessserver.multi.service;

import com.example.chessserver.multi.data.MoveReqDto;
import com.example.chessserver.multi.data.MoveResDto;
import com.example.chessserver.multi.data.PieceReqDto;
import com.example.chessserver.multi.data.PieceResDto;
import com.example.chessserver.multi.domain.Game;
import com.example.chessserver.multi.domain.Piece;
import com.example.chessserver.multi.domain.PieceMovement;
import com.example.chessserver.multi.domain.PieceSetting;
import com.example.chessserver.multi.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class MultiGameService {
    private final Map<String, PieceMovement> movements = new HashMap<>();
    private final Map<String, PieceSetting> settings = new HashMap<>();
    private final GameRepository gameRepository;
    private final MatchService matchService;

    public void savePieceSetting(PieceReqDto pieceReqDto) {
        settings.put(pieceReqDto.getUserId(), pieceReqDto.getPiece().toPieceSetting());
    }

    public PieceResDto getPieceSetting(String gameUuid, String userUuid) {
        Game game = gameRepository.findGameByUuid(gameUuid);
        String targetUuid;
        if(game.getPlayer1().getUuid().equals(userUuid)) {
            targetUuid = game.getPlayer2().getUuid();
            return PieceResDto.builder()
                    .piece(settings.get(targetUuid).toPieceDto())
                    .sequence(1).build();
        }
        targetUuid = game.getPlayer1().getUuid();
        return PieceResDto.builder()
                .piece(settings.get(targetUuid).toPieceDto())
                .sequence(2).build();
    }
    public void saveMovement(MoveReqDto moveReqDto) {
        PieceMovement pieceMovement = new PieceMovement(
                Piece.toPiece(moveReqDto.getPiece()),
                moveReqDto.getStart(),
                moveReqDto.getEnd());
        movements.put(moveReqDto.getUserId(), pieceMovement);
    }

    public MoveResDto getMovement(String gameUuid, String userUuid) {
        Game game = gameRepository.findGameByUuid(gameUuid);
        PieceMovement pieceMovement;
        if(game.getPlayer1().getUuid().equals(userUuid)) {
            pieceMovement = movements.get(game.getPlayer2().getUuid());
        }
        else{
            pieceMovement = movements.get(game.getPlayer1().getUuid());
        }
        return MoveResDto.builder()
                .piece(pieceMovement.getPiece().toString())
                .start(pieceMovement.getStart())
                .end(pieceMovement.getEnd()).build();
    }

    public void deleteGame(String gameUuid) {
        matchService.deleteGame(gameUuid);
        gameRepository.removeGame(gameUuid);
    }
}
