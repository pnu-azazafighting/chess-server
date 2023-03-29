package com.example.chessserver.user;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    public String generateUserId() {
        return UUID.randomUUID().toString();
    }
}
