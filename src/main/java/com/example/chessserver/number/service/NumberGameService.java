package com.example.chessserver.number.service;

import com.example.chessserver.chess.data.MoveReqDto;
import com.example.chessserver.chess.data.MoveResDto;
import com.example.chessserver.chess.domain.Game;
import com.example.chessserver.chess.domain.PieceMovement;
import com.example.chessserver.chess.exception.NoContentException;
import com.example.chessserver.chess.exception.NoGameException;
import com.example.chessserver.chess.repository.GameRepository;
import com.example.chessserver.match.service.MatchServiceRouter;
import com.example.chessserver.number.data.NumSetDto;
import com.example.chessserver.number.data.NumSetRes;
import com.example.chessserver.number.domain.NumSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class NumberGameService {
    private final Map<String, PieceMovement> movements = new HashMap<>();
    private final Map<String, NumSetting> settings = new HashMap<>(); //TODO: 세팅 값들 스케쥴러 삭제
    private final GameRepository gameRepository;
    private final MatchServiceRouter matchServiceRouter;

    public void savePieceSetting(String gameUuid, NumSetDto numSetDto) {
        settings.put(numSetDto.getUserId(), numSetDto.toNumSetting());

        //String id = findOtherPlayerId(gameUuid, numSetDto.getUserId());
        //settings.remove(id);
    }

    public NumSetRes getPieceSetting(String gameUuid, String userUuid) {
        Game game = gameRepository.findGameByUuid(gameUuid).orElseThrow(NoGameException::new);
        String targetUuid = findOtherPlayerId(gameUuid, userUuid);
        int sequence = game.getPlayer1().getUuid().equals(userUuid) ? 1 : 2;
        if (Objects.nonNull(settings.get(targetUuid))) {
            return NumSetRes.builder()
                    .nums(settings.get(targetUuid).toNumSetDto().getNums())
                    .sequence(sequence)
                    .build();
        }

        throw new NoContentException();
    }

    public void saveMovement(String gameUuid, MoveReqDto moveReqDto) {
        PieceMovement pieceMovement = new PieceMovement(
                moveReqDto.getStart(),
                moveReqDto.getEnd());
        movements.put(moveReqDto.getUserId(), pieceMovement);

        String id = findOtherPlayerId(gameUuid, moveReqDto.getUserId());
        movements.remove(id);
    }

    public MoveResDto getMovement(String gameUuid, String userUuid) {
        PieceMovement pieceMovement = movements.get(findOtherPlayerId(gameUuid, userUuid));
        if (Objects.nonNull(pieceMovement)) {
            return MoveResDto.builder()
                    .start(pieceMovement.getStart())
                    .end(pieceMovement.getEnd()).build();

        }
        throw new NoContentException();
    }

    public void deleteGame(String gameUuid, String type) {
        matchServiceRouter.getMatchServiceImpl(type).deleteGame(gameUuid);
        gameRepository.removeGame(gameUuid);
    }

    private String findOtherPlayerId(String gameUuid, String userUuid) {
        Game game = gameRepository.findGameByUuid(gameUuid).orElseThrow(NoGameException::new);
        if (game.getPlayer1().getUuid().equals(userUuid)) {
            return game.getPlayer2().getUuid();
        }
        return game.getPlayer1().getUuid();
    }
    //TODO: 게임 중단 시 게임 삭제
}
