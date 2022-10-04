package org.game.connect4.model;

/**
 * GridPosition indicates the position of a token in the Connect Four game grid.
 */
public class GridPosition {
    /**
     * Row number of the token position (starts from 1...)
     */
    private final int row;
    /**
     * Column number of the token position (starts from 1...)
     */
    private final int column;

    /**
     * Constructs a GridPosition given the row and column number
     * @param row Row number of a token
     * @param column Column number of a token
     */
    public GridPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the row number of a token
     * @return row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column number of a token
     * @return column number
     */
    public int getColumn() {
        return column;
    }
}
