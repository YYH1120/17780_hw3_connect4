package org.game.connect4.exception;

public class NoMovesAvailable extends RuntimeException{

    public NoMovesAvailable() {
        super("No more moves possible! The game has ended.");
    }

    public NoMovesAvailable(String message) {
        super(message);
    }

    public NoMovesAvailable(String message, Throwable cause) {
        super(message, cause);
    }
}
