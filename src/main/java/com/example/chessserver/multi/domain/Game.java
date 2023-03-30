package com.example.chessserver.multi.domain;

import com.example.chessserver.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "games")
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    @OneToOne
    @JoinColumn(name = "player1_id")
    private User player1;
    @OneToOne
    @JoinColumn(name = "player2_id")
    private User player2;

    public Game(String uuid, User player1, User player2) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
    }

}
