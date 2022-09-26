package org.game.connect4;

import org.game.connect4.exception.InvalidMoveException;
import org.game.connect4.util.GameMode;
import org.game.connect4.util.GameStatus;
import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GameGrid gameGrid;
    private final GameMode gameMode;  // 0:pvp, 1:pvc, 2:cvc
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game (GameMode gameMode, String name1, String name2){
        this.gameMode = gameMode;
        this.gameGrid = new GameGrid();
        this.player1 = new Player(PlayerID.PLAYER_1, name1, TokenColor.RED);
        this.player2 = new Player(PlayerID.PLAYER_2, name2, TokenColor.BLUE);
    }

    public Game (GameMode gameMode, int height, int width, String name1, String name2){
        this.gameMode = gameMode;
        this.gameGrid = new GameGrid(height, width);
        this.player1 = new Player(PlayerID.PLAYER_1, name1, TokenColor.RED);
        this.player2 = new Player(PlayerID.PLAYER_2, name2, TokenColor.BLUE);
    }

    public void Initialization (){
        if (this.gameMode == GameMode.PLAYER_VS_COMPUTER){
            this.player2.SetComputer();
        }
        if (this.gameMode == GameMode.COMPUTER_VS_COMPUTER){
            this.player1.SetComputer();
            this.player2.SetComputer();
        }
        this.currentPlayer = this.player1;
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

    public GameStatus playMove(int column) {
        if(!isValidMove(column))
            throw new InvalidMoveException();

        this.gameGrid.getGrid().get(column).add(this.currentPlayer.tokenColor.getSymbol());
        return this.CheckWin(column);
    }

    public boolean isValidMove(int column) {
        return column >= 0 && this.gameGrid.getGrid().get(column).size() < this.gameGrid.getHeight();
    }

    public void switchPlayer() {
        this.currentPlayer = this.currentPlayer == this.player1 ? this.player2 : this.player1;
    }

    // 0： game continue, 1: player1 won, 2: player2 won, 3: tie
    // after checkWin, we change the current player
    public GameStatus CheckWin(int lastCol){
        int lastRow = this.gameGrid.getGrid().get(lastCol).size()-1;
        int maxContinueR = 1;
        int maxContinueB = 1;
        int numContinueR = 1;
        int numContinueB = 1;
        int row, col;

        // check row
        row = lastRow;
        col = lastCol;
        // check player1 for row condition
        while (this.currentPlayer.getId() == PlayerID.PLAYER_1 && col - 1 >=0 && row < this.gameGrid.getGrid().get(col).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col - 1).get(row) ){
            numContinueR += 1;
            col -= 1;
        }
        col = lastCol;
        while (this.currentPlayer.getId() == PlayerID.PLAYER_1 && col + 1 < this.gameGrid.getGrid().size() && row < this.gameGrid.getGrid().get(col).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col + 1).get(row) ){
            numContinueR += 1;
            col += 1;
        }
        //check player2 for row condition
        while (this.currentPlayer.getId() == PlayerID.PLAYER_2 && col - 1 >=0 && row < this.gameGrid.getGrid().get(col).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col - 1).get(row) ){
            numContinueB += 1;
            col -= 1;
        }
        col = lastCol;
        while (this.currentPlayer.getId() == PlayerID.PLAYER_2 && col + 1 < this.gameGrid.getGrid().size() && row < this.gameGrid.getGrid().get(col).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col + 1).get(row) ){
            numContinueB += 1;
            col += 1;
        }
        maxContinueR = Math.max(maxContinueR, numContinueR);
        maxContinueB = Math.max(maxContinueB, numContinueB);

        // check col
        row = lastRow;
        col = lastCol;
        numContinueR = 1;
        numContinueB = 1;
        // check player1 for col condition
        while (this.currentPlayer.getId() == PlayerID.PLAYER_1 && row - 1 >=0 && this.gameGrid.getGrid().get(lastCol).get(row) == this.gameGrid.getGrid().get(lastCol).get(row - 1) ){
            numContinueR += 1;
            row -= 1;
        }
        //check player2 for row condition
        while (this.currentPlayer.getId() == PlayerID.PLAYER_2 && row - 1 >=0 && this.gameGrid.getGrid().get(lastCol).get(row) == this.gameGrid.getGrid().get(lastCol).get(row - 1) ){
            numContinueB += 1;
            row -= 1;
        }
        maxContinueR = Math.max(maxContinueR, numContinueR);
        maxContinueB = Math.max(maxContinueB, numContinueB);

        // check diagonal
        row = lastRow;
        col = lastCol;
        numContinueR = 1;
        numContinueB = 1;

        // check player1 for diagonal condition
        while (this.currentPlayer.getId() == PlayerID.PLAYER_1 && col - 1 >=0
                && row < this.gameGrid.getGrid().get(col).size() && row-1 < this.gameGrid.getGrid().get(col-1).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col - 1).get(row-1) ){
            numContinueB += 1;
            col -= 1;
            row -= 1;
        }

        while (this.currentPlayer.getId() == PlayerID.PLAYER_1 && col + 1 < this.gameGrid.getGrid().size()
                && row < this.gameGrid.getGrid().get(col).size() && row+1 < this.gameGrid.getGrid().get(col+1).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col + 1).get(row+1) ){
            numContinueB += 1;
            col += 1;
            row += 1;
        }

        // check player2 for diagonal condition
        while (this.currentPlayer.getId() == PlayerID.PLAYER_2 && col - 1 >=0
                && row < this.gameGrid.getGrid().get(col).size() && row-1 < this.gameGrid.getGrid().get(col-1).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col - 1).get(row-1) ){
            numContinueB += 1;
            col -= 1;
            row -= 1;
        }

        while (this.currentPlayer.getId() == PlayerID.PLAYER_2 && col + 1 < this.gameGrid.getGrid().size()
                && row < this.gameGrid.getGrid().get(col).size() && row+1 < this.gameGrid.getGrid().get(col+1).size()
                && this.gameGrid.getGrid().get(col).get(row) == this.gameGrid.getGrid().get(col + 1).get(row+1) ){
            numContinueB += 1;
            col += 1;
            row += 1;
        }

        //case 1: player1 win, return 1
        if (maxContinueR >=4){
            return GameStatus.PLAYER_1_WINS;
        }

        //case 2: player2 win, return 2
        if (maxContinueB >= 4){
            return GameStatus.PLAYER_2_WINS;
        }

        //case 3: tie, return 3
        int totalOccupiedGrid = 0;
        for (List<Character> c : this.gameGrid.getGrid()){
            totalOccupiedGrid += c.size();
        }
        if (totalOccupiedGrid == this.gameGrid.getHeight() * this.gameGrid.getWidth()){
            return GameStatus.TIE;
        }

        // case 4: game continue, return 0
        return GameStatus.CONTINUE;
    }


}
