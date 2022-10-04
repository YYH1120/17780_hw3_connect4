package org.game.connect4;

import org.game.connect4.model.ConnectFourStatus;
import org.game.connect4.model.GameGrid;
import org.game.connect4.model.GridPosition;
import org.game.connect4.model.Player;
import org.game.connect4.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ConnectFourGame contains the core logic of the ConnectFour game and all the functions required to play the game.
 * It provides the function of playing a move, switching players and checking game status.
 * It is initialized with the game grid, game mode and the names of the players.
 * <br />
 * Example Code -
 * <pre>
 *     {@code
 *     ConnectFourInitializer initializer = new ConnectFourInitializer();
 *     ConnectFourGame game = initializer.initializeDefaultPlayerVsPlayer(player1Name, player2Name);
 *     ConnectFourStatus gameStatus;
 *         while (true){
 *             System.out.println(game.getCurrentPlayer().getName() + " please input the
 *             column index (from 1 to "+ (game.getGameGrid().getWidth()) + ") you want to put a checker: ");
 *             int colIndex = input.nextInt();
 *             // Handle invalid move
 *             if (!game.playMove(colIndex)){
 *                 System.out.println("Invalid Move! Try again");
 *                 continue;
 *             }
 *             game.getGameGrid().displayGrid();
 *             gameStatus = game.checkGameStatus(colIndex);
 *             if(gameStatus.getGameStatus() != GameStatus.CONTINUE)
 *                 break;
 *             game.switchPlayer();
 *         }
 *         if (gameStatus.getGameStatus() == GameStatus.PLAYER_1_WINS){
 *             System.out.println("Congrats! " + game.getPlayer1().getName() + " has won the game!");
 *             gameStatus.displayWinningSequence();
 *         }
 *         else if (gameStatus.getGameStatus() == GameStatus.PLAYER_2_WINS){
 *             System.out.println("Congrats! " + game.getPlayer2().getName() + " has won the game!");
 *             gameStatus.displayWinningSequence();
 *         }
 *         else{
 *             System.out.println("Game Tied!");
 *         }
 *         }
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
     * Constructs a ConnectFour game with gameGrid, gameMode, player1 and player2
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
     * Set the current player
     * @param currentPlayer player of the current turn to set
     */
    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Get the current player
     * @return the player of the current turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get the gameGrid
     * @return the gameGrid of the game
     */
    public GameGrid getGameGrid() {
        return gameGrid;
    }

    /**
     * Get the gameMode
     * @return the gameMode of the game
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Get the first player
     * @return the first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Get the second player
     * @return the second player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Get the winning sequence of the game
     * @return a list of GridPosition sorted by row number representing the winning sequence
     */
    private List<GridPosition> getWinningSequence() {
        return winningSequence;
    }

    /**
     * Set the winning sequence of the game
     * @param winningSequence a sequence of tokens having length >= 4
     */
    private void setWinningSequence(List<GridPosition> winningSequence) {
        this.winningSequence = winningSequence;
    }

    /**
     * Check whether the movement is valid
     * @param column the column number (starting from 1...) where the current player wants to put a token
     * @return true for the valid movement and false for the invalid movement
     */
    public boolean isValidMove(int column) {
        return column >= 1 && column <= getGameGrid().getWidth() && getGameGrid().getGrid().get(column).size() < getGameGrid().getHeight();
    }

    /**
     * Switching the current player's turn into the other player's turn
     * @return the new current player after switching
     */
    public Player switchPlayer() {
        setCurrentPlayer(getCurrentPlayer() == getPlayer1() ? getPlayer2() : getPlayer1());
        return getCurrentPlayer();
    }

    /**
     * Drop the token into the grid according to the player's movement
     * @param column the column number (starting from 1...) where the current player wants to put a token
     * @return true if the move is played successfully
     */
    public boolean playMove(int column) {
        if(!isValidMove(column))
            return false;
        column -= 1;
        getGameGrid().getGrid().get(column).add(getCurrentPlayer().getTokenColor().getSymbol());
        return true;
    }

    /**
     * Return all the possible column numbers for users to put their tokens
     * @return the list of column numbers (starting from 1...) which are available for a movement
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
     * Check the game status to determine if players have won/tied/or should continue
     * @param lastCol the number of column (starting from 1...) where the last player placed the token
     * @return ConnectFourStatus object which stores both game status and winning sequences
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
     * Check if there are four or more consecutive tokens of the same color in row and
     * set the winning sequence if some player wins
     * @param row the index number of row that has the token placed by the last player who played the last turn
     * @param col the index number of column that has the token placed by the player who played the last turn
     * @param currColor the color of the token placed by the player who played the last turn
     * @return true if the length of the longest row sequence is greater than or equal to 4 and false otherwise
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
     * Check if there are four or more consecutive tokens of the same color in a column and
     * set the winning sequence if some player wins
     * @param row the index number of row that has the token placed by the last player who played the last turn
     * @param col the index number of column that has the token placed by the player who played the last turn
     * @param currColor the color of the token placed by the player who played the last turn
     * @return true if the length of the longest column sequence is greater than or equal to 4 and false otherwise
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
     * Check if there are four or more consecutive tokens of the same color in right diagonal and
     * set the winning sequence if some player wins
     * @param row the index number of row that has the token placed by the last player who played the last turn
     * @param col the index number of column that has the token placed by the player who played the last turn
     * @param currColor the color of the token placed by the player who played the last turn
     * @return true if the length of the longest right diagonal sequence is greater than or equal to 4 and false otherwise
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
     * Check if there are four or more consecutive tokens of the same color in left diagonal and
     * set the winning sequence if some player wins
     * @param row the index number of row that has the token placed by the last player who played the last turn
     * @param col the index number of column that has the token placed by the player who played the last turn
     * @param currColor the color of the token placed by the player who played the last turn
     * @return true if the length of the longest left diagonal sequence is greater than or equal to 4 and false otherwise
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
