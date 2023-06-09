package com.example.chessserver.user.service;

import com.example.chessserver.user.domain.User;
import com.example.chessserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String saveUser(String name) {
        User user = new User(name, generateUUId());
        userRepository.save(user);
        return user.getUuid();
    }

    public String generateUUId() {
        return UUID.randomUUID().toString();
    }
}
