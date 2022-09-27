package org.game.connect4;

import org.game.connect4.exception.IllegalMoveException;
import org.game.connect4.util.*;

import java.util.HashMap;
import java.util.List;

/**
 * ConnectFourGame contains the main logic of the ConnectFour game and all the implementation of functions to play the game.
 * It provides the function of playing a move, switching players as well as checking game status.
 * It initializes with the game grid, game mode, the names of the players and the current player as well.
 *
 * Example Code -
 * ConnectFourGame game = initializer.initializeDefaultPlayerVsPlayer(player1Name, player2Name);
 *  while (true){
 *  System.out.println(game.getCurrentPlayer().name() + " please input the column index (from 1 to "+ (game.getGameGrid().getWidth()) + ") you want to put a checker: ");
 *  int colIndex = input.nextInt() - 1;
 *  game.playMove(colIndex);
 *  game.getGameGrid().displayGrid();
 *  gameStatus = game.checkGameStatus(colIndex);
 *  if(gameStatus != GameStatus.CONTINUE)
 *      break;
 *  game.switchPlayer();
 *  }
 *  if (gameStatus == GameStatus.PLAYER_1_WINS){
 *     System.out.println("Congrats! " + game.getPlayer1().name() + " has won the game!");
 *  }
 *  else if (gameStatus == GameStatus.PLAYER_2_WINS){
 *     System.out.println("Congrats! " + game.getPlayer2().name() + " has won the game!");
 *  }
 *  else{
 *      System.out.println("Game Tied!");
 * }
 */
public class ConnectFourGame {
    private final GameGrid gameGrid;
    private final GameMode gameMode;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

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
    }

    /**
     * Set the current player.
     * @param currentPlayer player of the current turn to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
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

    /**
     * Check whether the movement is valid.
     * @param column the index of the column that the player want to put the checker
     * @return True for the valid movement and False for the invalid movement.
     */
    public boolean isValidMove(int column) {
        return column >= 0 && column < getGameGrid().getWidth() && getGameGrid().getGrid().get(column).size() < getGameGrid().getHeight();
    }

    /**
     * Switching the current player's turn into the other player's turn.
     */
    public void switchPlayer() {
        setCurrentPlayer(getCurrentPlayer() == getPlayer1() ? getPlayer2() : getPlayer1());
    }

    /**
     * Drop the checker into the grid according to the player's movement.
     * @param column the index of the column that the current player want to put a checker
     */
    public void playMove(int column) {
        if(!isValidMove(column))
            throw new IllegalMoveException();

        getGameGrid().getGrid().get(column).add(getCurrentPlayer().tokenColor().getSymbol());
    }

    /**
     * Check the game status to determine if players have won/tied/or should continue.
     * @param lastCol the index number of column that checker placed by the player who performed the last turn
     * @return the status of the Game as one of 'CONTINUE', 'PLAYER_1_WINS', 'PLAYER_2_WINS' or 'TIE'
     */
    public GameStatus checkGameStatus(int lastCol){
        int lastRow = getGameGrid().getGrid().get(lastCol).size()-1;
        Character currColor = getCurrentPlayer().tokenColor().getSymbol();

        HashMap<Character,Integer> maxContinue = new HashMap<>();
        maxContinue.put(TokenColor.RED.getSymbol(),1);
        maxContinue.put(TokenColor.BLUE.getSymbol(),1);

        // Check Row
        int resCheckRow = checkRow(lastRow,lastCol,currColor);
        maxContinue.put(currColor, Math.max(maxContinue.get(currColor),resCheckRow));

        // Check Column
        int resCheckCol = checkCol(lastRow,lastCol,currColor);
        maxContinue.put(currColor, Math.max(maxContinue.get(currColor),resCheckCol));

        // Check Right Diagonal
        int resCheckRightDiagonal = checkRightDiagonal(lastRow,lastCol,currColor);
        maxContinue.put(currColor, Math.max(maxContinue.get(currColor),resCheckRightDiagonal));

        // Check Left Diagonal
        int resCheckLeftDiagonal = checkLeftDiagonal(lastRow,lastCol,currColor);
        maxContinue.put(currColor, Math.max(maxContinue.get(currColor),resCheckLeftDiagonal));

        //case 1: Player1 Win
        if (maxContinue.get(getPlayer1().tokenColor().getSymbol()) >=4){
            return GameStatus.PLAYER_1_WINS;
        }

        //case 2: Player2 Win
        if (maxContinue.get(getPlayer2().tokenColor().getSymbol()) >= 4){
            return GameStatus.PLAYER_2_WINS;
        }

        //case 3: Tie
        int totalOccupiedGrid = 0;
        for (List<Character> c : getGameGrid().getGrid()){
            totalOccupiedGrid += c.size();
        }
        if (totalOccupiedGrid == getGameGrid().getHeight() * getGameGrid().getWidth()){
            return GameStatus.TIE;
        }

        // case 4: Game Continue
        return GameStatus.CONTINUE;
    }

    /**
     * Check if there are four consecutive checkers of the same color in row.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in row
     */
    private int checkRow (int row, int col, Character currColor){
        int maxContinueNum = 1;
        while (row - 1 >= 0 && currColor == getGameGrid().getGrid().get(col).get(row-1)){
            maxContinueNum += 1;
            row -= 1;
        }
        return maxContinueNum;
    }

    /**
     * Check if there are four consecutive checkers of the same color in column.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in column
     */
    private int checkCol (int row, int col, Character currColor){
        int maxContinueNum = 1;
        int tempCol = col;
        while (col - 1>=0 && row<getGameGrid().getGrid().get(col - 1).size() && currColor == getGameGrid().getGrid().get(col - 1).get(row)){
            maxContinueNum += 1;
            col -= 1;
        }
        col = tempCol;
        while (col + 1<getGameGrid().getWidth() && row<getGameGrid().getGrid().get(col + 1).size() && currColor == getGameGrid().getGrid().get(col + 1).get(row)){
            maxContinueNum += 1;
            col += 1;
        }
        return maxContinueNum;
    }

    /**
     * Check if there are four consecutive checkers of the same color in right diagonal.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in column in right diagonal
     */
    private int checkRightDiagonal (int row, int col, Character currColor){
        int maxContinueNum = 1;
        int tempRow = row;
        int tempCol = col;
        while (col - 1>=0 && row-1>=0 && row - 1<getGameGrid().getGrid().get(col - 1).size() && currColor == getGameGrid().getGrid().get(col - 1).get(row - 1)){
            maxContinueNum += 1;
            col -= 1;
            row -= 1;
        }
        row = tempRow;
        col = tempCol;
        while (col + 1<getGameGrid().getWidth() && row + 1<getGameGrid().getHeight() && row + 1<getGameGrid().getGrid().get(col + 1).size()
                && currColor == getGameGrid().getGrid().get(col + 1).get(row + 1)){
            maxContinueNum += 1;
            col += 1;
            row += 1;
        }
        return maxContinueNum;
    }

    /**
     * Check if there are four consecutive checkers of the same color in left diagonal.
     * @param row the index number of row that checker placed by the player who performed the last turn
     * @param col the index number of column that checker placed by the player who performed the last turn
     * @param currColor the color of the checker placed by the player who performed the last turn
     * @return the maximum number of consecutive checkers in column in left diagonal
     */
    private int checkLeftDiagonal (int row, int col, Character currColor){
        int maxContinueNum = 1;
        int tempRow = row;
        int tempCol = col;
        while (col + 1<getGameGrid().getWidth() && row-1>=0 && row - 1<getGameGrid().getGrid().get(col + 1).size() && currColor == getGameGrid().getGrid().get(col + 1).get(row - 1)){
            maxContinueNum += 1;
            col += 1;
            row -= 1;
        }
        row = tempRow;
        col = tempCol;
        while (col -1>=0 && row +1<getGameGrid().getHeight() && row + 1<getGameGrid().getGrid().get(col - 1).size()
                && currColor == getGameGrid().getGrid().get(col - 1).get(row + 1)){
            maxContinueNum += 1;
            col -= 1;
            row += 1;
        }
        return maxContinueNum;
    }

}
