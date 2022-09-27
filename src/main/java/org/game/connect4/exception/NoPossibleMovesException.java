package org.game.connect4.exception;

/**
 * This custom exception is thrown when a player cannot make any legal moves in the ConnectFour grid.
 */
public class NoPossibleMovesException extends RuntimeException {

    /**
     * Constructs an NoPossibleMovesException with a default detail message.
     */
    public NoPossibleMovesException() {
        super("No more moves possible! The game has ended.");
    }

    /**
     * Constructs an NoPossibleMovesException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param message - the detail message.
     */
    public NoPossibleMovesException(String message) {
        super(message);
    }
}
