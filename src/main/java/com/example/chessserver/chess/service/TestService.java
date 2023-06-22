package com.example.chessserver.chess.service;

import com.example.chessserver.chess.repository.GameRepository;
import com.example.chessserver.match.service.ChessMatchServiceImpl;
import com.example.chessserver.match.service.NumberMatchServiceImpl;
import com.example.chessserver.number.service.NumberGameService;
import com.example.chessserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public void deleteAllData() {
        userRepository.deleteAll();
        gameRepository.deleteAll();
        NumberMatchServiceImpl.reset();
        ChessMatchServiceImpl.reset();
        ChessGameService.reset();
        NumberGameService.reset();
    }

}
