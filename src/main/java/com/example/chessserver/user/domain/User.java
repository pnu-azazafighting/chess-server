package com.example.chessserver.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String uuid;

    public User(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }
}
