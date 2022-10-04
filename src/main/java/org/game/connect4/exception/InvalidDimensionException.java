package org.game.connect4.exception;

/**
 * This custom exception is thrown when invalid dimensions are used to create a the ConnectFour grid.
 */
public class InvalidDimensionException extends IllegalArgumentException {

    /**
     * Constructs an InvalidDimensionException with a default detail message -
     * "Entered width/height is invalid! The value should be >= 4."
     */
    public InvalidDimensionException() {
        super("Entered width/height is invalid! The value should be >= 4.");
    }

    /**
     * Constructs an InvalidParameterException with the specified detail message
     * A detail message is a String that describes this particular exception
     * @param message the detail message
     */
    public InvalidDimensionException(String message) {
        super(message);
    }
}
