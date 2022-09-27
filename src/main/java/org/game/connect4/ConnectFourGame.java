package org.game.connect4;

import org.game.connect4.util.GameStatus;

public interface ConnectFourGame {

    /**
     * Check whether the movement is valid.
     * @param column the index of the column that the player want to put the checker
     * @return True for the valid movement and False for the invalid movement.
     */
    boolean isValidMove(int column);

    /**
     * Drop the checker into the grid according to the player's movement.
     * @param column the index of the column that the current player want to put a checker
     */
    void playMove(int column);

    /**
     * Check if the game is over and if there is a winner.
     * @param lastCol the index number of column that checker placed by the player who performed the last turn
     * @return the status of the Game from GameStatus
     */
    GameStatus checkWin(int lastCol);

    /**
     * Switch the current player's turn into the other player's turn.
     */
    void switchPlayer();

}
