package org.game.connect4;

import org.game.connect4.util.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GameBoard gameBoard;
    private final int gameMode;  // 0:pvp, 1:pvc, 2:cvc
    private final List<Player> playerList = new ArrayList<>();
    private Player currentPlayer;
    private int lastCol; // the column index of last movement

    public Game (int gameMode, String name1, String name2){
        this.gameMode = gameMode;
        this.gameBoard = new GameBoard();
        Player player1 = new Player(this.playerList.size(), name1);
        this.playerList.add(player1);
        Player player2 = new Player(this.playerList.size(), name2);
        this.playerList.add(player2);
    }

    public Game (int gameMode, int height, int width, String name1, String name2){
        this.gameMode = gameMode;
        this.gameBoard = new GameBoard(height, width);
        Player player1 = new Player(this.playerList.size(), name1);
        this.playerList.add(player1);
        Player player2 = new Player(this.playerList.size(), name2);
        this.playerList.add(player2);
    }

    public void Initialization (){
        if (this.gameMode == 1){
            this.playerList.get(1).SetComputer();
        }
        if (this.gameMode == 2){
            this.playerList.get(0).SetComputer();
            this.playerList.get(1).SetComputer();
        }
        this.currentPlayer = this.playerList.get(0);
    }


    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // 0： game continue, 1: player1 won, 2: player2 won, 3: tie
    // after checkWin, we change the current player
    public GameStatus CheckWin(){
        int lastCol = this.lastCol;
        int lastRow = this.gameBoard.getGrid().get(lastCol).size()-1;
        int maxContinueR = 1;
        int maxContinueB = 1;
        int numContinueR = 1;
        int numContinueB = 1;
        int row, col;

        // check row
        row = lastRow;
        col = lastCol;
        // check player1 for row condition
        while (this.currentPlayer.getId() == 0 && col - 1 >=0 && row < this.gameBoard.getGrid().get(col).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col - 1).get(row) ){
            numContinueR += 1;
            col -= 1;
        }
        col = lastCol;
        while (this.currentPlayer.getId() == 0 && col + 1 < this.gameBoard.getGrid().size() && row < this.gameBoard.getGrid().get(col).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col + 1).get(row) ){
            numContinueR += 1;
            col += 1;
        }
        //check player2 for row condition
        while (this.currentPlayer.getId() == 1 && col - 1 >=0 && row < this.gameBoard.getGrid().get(col).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col - 1).get(row) ){
            numContinueB += 1;
            col -= 1;
        }
        col = lastCol;
        while (this.currentPlayer.getId() == 1 && col + 1 < this.gameBoard.getGrid().size() && row < this.gameBoard.getGrid().get(col).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col + 1).get(row) ){
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
        while (this.currentPlayer.getId() == 0 && row - 1 >=0 && this.gameBoard.getGrid().get(lastCol).get(row) == this.gameBoard.getGrid().get(lastCol).get(row - 1) ){
            numContinueR += 1;
            row -= 1;
        }
        //check player2 for row condition
        while (this.currentPlayer.getId() == 1 && row - 1 >=0 && this.gameBoard.getGrid().get(lastCol).get(row) == this.gameBoard.getGrid().get(lastCol).get(row - 1) ){
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
        while (this.currentPlayer.getId() == 0 && col - 1 >=0
                && row < this.gameBoard.getGrid().get(col).size() && row-1 < this.gameBoard.getGrid().get(col-1).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col - 1).get(row-1) ){
            numContinueB += 1;
            col -= 1;
            row -= 1;
        }

        while (this.currentPlayer.getId() == 0 && col + 1 < this.gameBoard.getGrid().size()
                && row < this.gameBoard.getGrid().get(col).size() && row+1 < this.gameBoard.getGrid().get(col+1).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col + 1).get(row+1) ){
            numContinueB += 1;
            col += 1;
            row += 1;
        }

        // check player2 for diagonal condition
        while (this.currentPlayer.getId() == 1 && col - 1 >=0
                && row < this.gameBoard.getGrid().get(col).size() && row-1 < this.gameBoard.getGrid().get(col-1).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col - 1).get(row-1) ){
            numContinueB += 1;
            col -= 1;
            row -= 1;
        }

        while (this.currentPlayer.getId() == 1 && col + 1 < this.gameBoard.getGrid().size()
                && row < this.gameBoard.getGrid().get(col).size() && row+1 < this.gameBoard.getGrid().get(col+1).size()
                && this.gameBoard.getGrid().get(col).get(row) == this.gameBoard.getGrid().get(col + 1).get(row+1) ){
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
        for (List<Character> c : this.gameBoard.getGrid()){
            totalOccupiedGrid += c.size();
        }
        if (totalOccupiedGrid == this.gameBoard.getHeight() * this.gameBoard.getWidth()){
            return GameStatus.TIE;
        }

        // case 4: game continue, return 0
        return GameStatus.CONTINUE;
    }


}
