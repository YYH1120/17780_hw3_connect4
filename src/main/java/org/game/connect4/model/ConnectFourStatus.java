package org.game.connect4.model;

import org.game.connect4.util.GameStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * ConnectFourStatus indicates the status of the game after every move.
 */
public class ConnectFourStatus {
    /**
     * Status of the game - CONTINUE, PLAYER_1_WINS, PLAYER_2_WINS, TIE
     */
    private final GameStatus gameStatus;
    /**
     * Sequence of tokens that has won the game
     */
    private final List<GridPosition> winningSequence;

    /**
     * Constructs ConnectFourStatus with a given gameStatus
     * This should be used when gameStatus is either CONTINUE or TIE
     * @param gameStatus status of the game
     */
    public ConnectFourStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.winningSequence = new ArrayList<>();
    }

    /**
     * Constructs ConnectFourStatus with a given gameStatus and winning sequence
     * This should be used when either player has won the game
     * @param gameStatus status of the game
     * @param winningSequence the winning sequence
     */
    public ConnectFourStatus(GameStatus gameStatus, List<GridPosition> winningSequence) {
        this.gameStatus = gameStatus;
        this.winningSequence = winningSequence;
    }

    /**
     * Get the status of the Connect Four game
     * @return GameStatus enum indicating the status of the game
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Get the winning sequence of the Connect Four game
     * @return a list of GridPosition sorted by row number representing the winning sequence
     */
    public List<GridPosition> getWinningSequence() {
        return winningSequence;
    }

    /**
     * Display the winning sequence on the console
     */
    public void displayWinningSequence(){
        System.out.print("The winning sequences are:");
        for (GridPosition p : getWinningSequence()){
            System.out.print(" (" + p.getRow() + "," + p.getColumn() + ")");
        }
        System.out.println();
    }
}
