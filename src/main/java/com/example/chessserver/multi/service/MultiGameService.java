package com.example.chessserver.multi.service;

import com.example.chessserver.multi.data.MoveReqDto;
import com.example.chessserver.multi.data.MoveResDto;
import com.example.chessserver.multi.data.PieceReqDto;
import com.example.chessserver.multi.data.PieceResDto;
import com.example.chessserver.multi.domain.Game;
import com.example.chessserver.multi.domain.Piece;
import com.example.chessserver.multi.domain.PieceMovement;
import com.example.chessserver.multi.domain.PieceSetting;
import com.example.chessserver.multi.exception.NoContentException;
import com.example.chessserver.multi.exception.NoGameException;
import com.example.chessserver.multi.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class MultiGameService {
    private final Map<String, PieceMovement> movements = new HashMap<>();
    private final Map<String, PieceSetting> settings = new HashMap<>();
    private final GameRepository gameRepository;
    private final MatchService matchService;

    public void savePieceSetting(String gameUuid, PieceReqDto pieceReqDto) {
        settings.put(pieceReqDto.getUserId(), pieceReqDto.getPiece().toPieceSetting());

        String id = findOtherPlayerId(gameUuid, pieceReqDto.getUserId());
        settings.remove(id);
    }

    public PieceResDto getPieceSetting(String gameUuid, String userUuid) {
        Game game = gameRepository.findGameByUuid(gameUuid).orElseThrow(NoGameException::new);
        String targetUuid = findOtherPlayerId(gameUuid, userUuid);
        int sequence = game.getPlayer1().getUuid().equals(userUuid) ? 1 : 2;
        if(Objects.nonNull(settings.get(targetUuid))){
            return PieceResDto.builder()
                    .piece(settings.get(targetUuid).toPieceDto())
                    .sequence(sequence).build();
        }

        throw new NoContentException();
    }
    public void saveMovement(String gameUuid, MoveReqDto moveReqDto) {
        PieceMovement pieceMovement = new PieceMovement(
                Piece.toPiece(moveReqDto.getPiece()),
                moveReqDto.getStart(),
                moveReqDto.getEnd());
        movements.put(moveReqDto.getUserId(), pieceMovement);

        String id = findOtherPlayerId(gameUuid, moveReqDto.getUserId());
        movements.remove(id);
    }

    public MoveResDto getMovement(String gameUuid, String userUuid) {
        PieceMovement pieceMovement = movements.get(findOtherPlayerId(gameUuid, userUuid));
        if(Objects.nonNull(pieceMovement)) {
            return MoveResDto.builder()
                    .piece(pieceMovement.getPiece().toString())
                    .start(pieceMovement.getStart())
                    .end(pieceMovement.getEnd()).build();

        }
        throw new NoContentException();
    }

    public void deleteGame(String gameUuid) {
        matchService.deleteGame(gameUuid);
        gameRepository.removeGame(gameUuid);
    }

    private String findOtherPlayerId(String gameUuid, String userUuid) {
        Game game = gameRepository.findGameByUuid(gameUuid).orElseThrow(NoGameException::new);
        if(game.getPlayer1().getUuid().equals(userUuid)) {
            return game.getPlayer2().getUuid();
        }
        return game.getPlayer1().getUuid();
    }
}

