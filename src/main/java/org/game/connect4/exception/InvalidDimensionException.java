package org.game.connect4.exception;

/**
 * This custom exception is thrown when invalid dimensions are used to create a the ConnectFour grid.
 */
public class InvalidDimensionException extends RuntimeException {

    /**
     * Constructs an InvalidDimensionException with a default detail message.
     */
    public InvalidDimensionException() {
        super("Entered width/height is invalid!");
    }

    /**
     * Constructs an InvalidParameterException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param message the detail message.
     */
    public InvalidDimensionException(String message) {
        super(message);
    }
}
