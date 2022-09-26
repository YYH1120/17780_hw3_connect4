package org.game.connect4.exception;

public class InvalidMoveException extends RuntimeException{

    public InvalidMoveException() {
        super("This move is not valid!");
    }

    public InvalidMoveException(String message) {
        super(message);
    }

    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
