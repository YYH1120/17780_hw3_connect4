package org.game.connect4.util;

/**
 * GameStatus enum enumerates the different possible status of the ConnectFour game after every move.
 */
public enum GameStatus {
    /**
     * Represents the state when neither player has won or last; the game should continue
     */
    CONTINUE,
    /**
     * Represents the state when player 1 wins
     */
    PLAYER_1_WINS,
    /**
     * Represents the state when player 2 wins
     */
    PLAYER_2_WINS,
    /**
     * Represents the state when neither player wins and the game results in a tie
     */
    TIE
}
