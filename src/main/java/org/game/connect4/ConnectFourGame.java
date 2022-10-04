package org.game.connect4;

import org.game.connect4.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ConnectFourGame contains the main logic of the ConnectFour game and all the implementation of functions to play the game.
 * It provides the function of playing a move, switching players as well as checking game status.
 * It initializes with the game grid, game mode, the names of the players and the current player as well.
 * <br />
 * Example Code -
 * <pre>
 *     {@code
 *     ConnectFourInitializer initializer = new ConnectFourInitializer();
 *     ConnectFourGame game = initializer.initializeDefaultPlayerVsPlayer(player1Name, player2Name);
 *     while (true){
 *             System.out.println(game.getCurrentPlayer().name() + " please input the column index (from 1 to "+ (game.getGameGrid().getWidth()) + ") you want to put a checker: ");
 *             int colIndex = input.nextInt() - 1;
 *             game.playMove(colIndex);
 *             game.getGameGrid().displayGrid();
 *             gameStatus = game.checkGameStatus(colIndex);
 *             if(gameStatus != GameStatus.CONTINUE)
 *                 break;
 *             game.switchPlayer();
 *         }
 *         if (gameStatus == GameStatus.PLAYER_1_WINS){
 *             System.out.println("Congrats! " + game.getPlayer1().name() + " has won the game!");
 *         }
 *         else if (gameStatus == GameStatus.PLAYER_2_WINS){
 *             System.out.println("Congrats! " + game.getPlayer2().name() + " has won the game!");
 *         }
 *         else {
 *             System.out.println("Game Tied!");
 *         }
 *
 *     }
 * </pre>
 */
public class ConnectFourGame {
    private final GameGrid gameGrid;
    private final GameMode gameMode;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private List<GridPosition> winningSequence;

    /**
     * Constructs a ConnectFour game with gameGrid, gameMode, player1 and player2.
     * @param gameGrid the board of the game
     * @param gameMode one of the game modes: player to player, player to computer and computer to computer
     * @param player1 the first player
     * @param player2 the second player
     */
    public ConnectFourGame(GameGrid gameGrid, GameMode gameMode, Player player1, Player player2){
        this.gameGrid = gameGrid;
        this.gameMode = gameMode;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.winningSequence = new ArrayList<>();
    }

    /**
     * Set the current player.
     * @param currentPlayer player of the current turn to set
     */
    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Get the current player.
     * @return the player of the current turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get the gameGrid.
     * @return the gameGrid of the game
     */
    public GameGrid getGameGrid() {
        return gameGrid;
    }

    /**
     * Get the gameMode.
     * @return the gameMode of the game
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Get the first player.
     * @return the first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Get the second player.
     * @return the second player
     */
    public Player getPlayer2() {
        return player2;
    }

    public List<GridPosition> getWinningSequence() {
        return winningSequence;
    }

    private void setWinningSequence(List<GridPosition> winningSequence) {
        this.winningSequence = winningSequence;
    }

    /**
     * Check whether the movement is valid.
     * @param column the column number (starting from 1) that the current player wants to put a checker in
     * @return true for the valid movement and false for the invalid movement.
     */
    public boolean isValidMove(int column) {
        return column >= 0 && column < getGameGrid().getWidth() && getGameGrid().getGrid().get(column).size() < getGameGrid().getHeight();
    }

    /**
     * Switching the current player's turn into the other player's turn.
     * @return the new current player after switching
     */
    public Player switchPlayer() {
        setCurrentPlayer(getCurrentPlayer() == getPlayer1() ? getPlayer2() : getPlayer1());
        return getCurrentPlayer();
    }

    /**
     * Drop the checker into the grid according to the player's movement.
     * @param column the column number (starting from 1) that the current player wants to put a checker in
     * @return true if the move is played successfully
     */
    public boolean playMove(int column) {
        column -= 1;
        if(!isValidMove(column))
            return false;

        getGameGrid().getGrid().get(column).add(getCurrentPlayer().getTokenColor().getSymbol());
        return true;
    }

    /**
     * Return all the possible column numbers for users to put their checkers.
     * @return the list of column numbers (starting from 1) which are available for a movement
     */
    public List<Integer> getAllPossibleMoves(){
        List<Integer> possibleMove = new ArrayList<>();
        for (int index = 0; index < getGameGrid().getWidth(); index ++){
            if (isValidMove(index))
                possibleMove.add(index + 1);
        }
        return possibleMove;
    }

    /**
     * Check the game status to determine if players have won/tied/or should continue.
     * @param lastCol the index number of column that checker placed by the player who performed the last turn
     * @return the status of the Game as one of 'CONTINUE', 'PLAYER_1_WINS', 'PLAYER_2_WINS' or 'TIE'
     */
    public ConnectFourStatus checkGameStatus(int lastCol){
        lastCol -= 1;
        int lastRow = getGameGrid().getGrid().get(lastCol).size()-1;
        Character currColor = getCurrentPlayer().getTokenColor().getSymbol();

        if(checkRow(lastRow, lastCol, currColor) || checkCol(lastRow, lastCol, currColor) ||
        checkLeftDiagonal(lastRow, lastCol, currColor) || checkRightDiagonal(lastRow, lastCol, currColor)) {
            if (currColor == getPlayer1().getTokenColor().getSymbol()) {
                return new ConnectFourStatus(GameStatus.PLAYER_1_WINS, getWinningSequence());
            }
            else
                return new ConnectFourStatus(GameStatus.PLAYER_2_WINS, getWinningSequence());
        }
        else {
            int totalOccupiedGrid = 0;
            for (List<Character> c : getGameGrid().getGrid()){
                totalOccupiedGrid += c.size();
            }
            //case 3: Tie
            if (totalOccupiedGrid == getGameGrid().getHeight() * getGameGrid().getWidth()){
                return new ConnectFourStatus(GameStatus.TIE, getWinningSequence());
            }
            // case 4: Game Continue
            else {
                return new ConnectFourStatus(GameStatus.CONTINUE, getWinningSequence());
            }
        }
    }

    /**
     * Check if there are four consecutive checkers of the same color in row.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in row
     */
    private boolean checkRow (int row, int col, Character currColor){
        int maxSequence = 1;
        List<GridPosition> winningSequence = new ArrayList<>();
        winningSequence.add(0, new GridPosition(row + 1, col + 1));
        while (row - 1 >= 0 && currColor == getGameGrid().getGrid().get(col).get(row - 1)){
            maxSequence += 1;
            winningSequence.add(0,new GridPosition(row, col + 1));
            row -= 1;
        }
        if (maxSequence >= 4) {
            setWinningSequence(winningSequence);
            return true;
        }
        return false;
    }

    /**
     * Check if there are four consecutive checkers of the same color in column.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in column
     */
    private boolean checkCol (int row, int col, Character currColor){
        int maxSequence = 1;
        int tempCol = col;
        List<GridPosition> winningSequence = new ArrayList<>();
        winningSequence.add(new GridPosition(row + 1, col + 1));
        while (col - 1 >= 0 && row < getGameGrid().getGrid().get(col - 1).size() &&
                currColor == getGameGrid().getGrid().get(col - 1).get(row)){
            maxSequence += 1;
            winningSequence.add(0, new GridPosition(row + 1, col));
            col -= 1;
        }
        col = tempCol;

        while (col + 1 < getGameGrid().getWidth() && row < getGameGrid().getGrid().get(col + 1).size() &&
                currColor == getGameGrid().getGrid().get(col + 1).get(row)){
            maxSequence += 1;
            winningSequence.add(new GridPosition(row + 1, col + 2));
            col += 1;
        }
        if (maxSequence >= 4) {
            setWinningSequence(winningSequence);
            return true;
        }
        return false;
    }

    /**
     * Check if there are four consecutive checkers of the same color in right diagonal.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in column in right diagonal
     */
    private boolean checkRightDiagonal (int row, int col, Character currColor){
        int maxSequence = 1;
        int tempRow = row;
        int tempCol = col;
        List<GridPosition> winningSequence = new ArrayList<>();
        winningSequence.add(new GridPosition(row + 1, col + 1));

        while (col - 1 >= 0 && row - 1 >= 0 && row - 1 < getGameGrid().getGrid().get(col - 1).size() &&
                currColor == getGameGrid().getGrid().get(col - 1).get(row - 1)){
            maxSequence += 1;
            winningSequence.add(0, new GridPosition(row, col));
            col -= 1;
            row -= 1;
        }
        row = tempRow;
        col = tempCol;
        while (col + 1 < getGameGrid().getWidth() && row + 1 < getGameGrid().getHeight() &&
                row + 1 < getGameGrid().getGrid().get(col + 1).size() &&
                currColor == getGameGrid().getGrid().get(col + 1).get(row + 1)){
            maxSequence += 1;
            winningSequence.add(new GridPosition(row + 2, col + 2));
            col += 1;
            row += 1;
        }
        if (maxSequence >= 4) {
            setWinningSequence(winningSequence);
            return true;
        }
        return false;
    }

    /**
     * Check if there are four consecutive checkers of the same color in left diagonal.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in column in left diagonal
     */
    private boolean checkLeftDiagonal (int row, int col, Character currColor){
        int maxSequence = 1;
        int tempRow = row;
        int tempCol = col;
        List<GridPosition> winningSequence = new ArrayList<>();
        winningSequence.add(new GridPosition(row + 1, col + 1));

        while (col + 1 < getGameGrid().getWidth() && row - 1 >= 0 &&
                row - 1 < getGameGrid().getGrid().get(col + 1).size() &&
                currColor == getGameGrid().getGrid().get(col + 1).get(row - 1)){
            maxSequence += 1;
            winningSequence.add(0, new GridPosition(row, col + 2));
            col += 1;
            row -= 1;
        }
        row = tempRow;
        col = tempCol;
        while (col - 1 >= 0 && row + 1 < getGameGrid().getHeight() &&
                row + 1 < getGameGrid().getGrid().get(col - 1).size() &&
                currColor == getGameGrid().getGrid().get(col - 1).get(row + 1)){
            maxSequence += 1;
            winningSequence.add(new GridPosition(row + 2, col));
            col -= 1;
            row += 1;
        }
        if (maxSequence >= 4) {
            setWinningSequence(winningSequence);
            return true;
        }
        return false;
    }

}
