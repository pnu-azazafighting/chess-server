package com.example.chessserver.chess.domain;

public enum Piece {
    KING,
    BISHOP,
    ROOK,
    PAWN,
    TRAP;

    public static Piece toPiece(String p) {
        return Piece.valueOf(p.toUpperCase());
    }
}
