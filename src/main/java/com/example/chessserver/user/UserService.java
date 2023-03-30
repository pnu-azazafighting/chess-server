package com.example.chessserver.user;

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
