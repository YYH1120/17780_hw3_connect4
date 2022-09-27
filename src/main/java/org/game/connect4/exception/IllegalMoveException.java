package org.game.connect4.exception;

/**
 * This custom exception is thrown when a player makes an illegal move in the ConnectFour grid.
 */
public class IllegalMoveException extends RuntimeException {

    /**
     * Constructs an IllegalMoveException with a default detail message.
     */
    public IllegalMoveException() {
        super("This move is not valid!");
    }

    /**
     * Constructs an IllegalMoveException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param message - the detail message.
     */
    public IllegalMoveException(String message) {
        super(message);
    }

}