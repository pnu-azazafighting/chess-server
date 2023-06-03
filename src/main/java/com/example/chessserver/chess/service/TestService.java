package com.example.chessserver.chess.service;

import com.example.chessserver.chess.repository.GameRepository;
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
    }

}
