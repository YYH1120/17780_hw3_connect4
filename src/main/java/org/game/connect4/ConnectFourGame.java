package org.game.connect4;

import org.game.connect4.exception.IllegalMoveException;
import org.game.connect4.util.*;

import java.util.HashMap;
import java.util.List;

public class ConnectFourGame {
    private final GameGrid gameGrid;
    private final GameMode gameMode;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public ConnectFourGame(GameGrid gameGrid, GameMode gameMode, Player player1, Player player2) {
        this.gameGrid = gameGrid;
        this.gameMode = gameMode;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isValidMove(int column) {
        return column >= 0 && getGameGrid().getGrid().get(column).size() < getGameGrid().getHeight();
    }

    private void switchPlayer() {
        setCurrentPlayer(getCurrentPlayer() == getPlayer1() ? getPlayer2() : getPlayer1());
    }

    public GameStatus playMove(int column) {
        if(!isValidMove(column))
            throw new IllegalMoveException();

        getGameGrid().getGrid().get(column).add(getCurrentPlayer().tokenColor().getSymbol());
        getGameGrid().displayGrid();
        switchPlayer();
        return checkWin(column);
    }

    public GameStatus checkWin(int lastCol){
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

    private int checkRow (int row, int col, Character currColor){
        int maxContinueNum = 1;
        while (row - 1 >= 0 && currColor == getGameGrid().getGrid().get(col).get(row-1)){
            maxContinueNum += 1;
            row -= 1;
        }
        return maxContinueNum;
    }

    private int checkCol (int row, int col, Character currColor){
        int maxContinueNum = 1;
        int tempCol = col;
        while (col - 1>=0 && row<getGameGrid().getGrid().get(col - 1).size() && currColor == getGameGrid().getGrid().get(col - 1).get(row)){
            maxContinueNum += 1;
            col -= 1;
        }
        col = tempCol;
        while (col + 1<getGameGrid().getWidth() && row<getGameGrid().getGrid().get(tempCol + 1).size() && currColor == getGameGrid().getGrid().get(col + 1).get(row)){
            maxContinueNum += 1;
            col += 1;
        }
        return maxContinueNum;
    }

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
                && currColor == getGameGrid().getGrid().get(col - 1).get(row - 1)){
            maxContinueNum += 1;
            col += 1;
            row += 1;
        }
        return maxContinueNum;
    }

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
