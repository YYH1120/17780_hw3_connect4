package org.game.connect4.exception;

public class NoPossibleMovesException extends RuntimeException{

    public NoPossibleMovesException() {
        super("No more moves possible! The game has ended.");
    }

    public NoPossibleMovesException(String message) {
        super(message);
    }

    public NoPossibleMovesException(String message, Throwable cause) {
        super(message, cause);
    }
}
