package org.game.connect4;

public class GameBoard {
    int length;
    int breadth;
    GridPosition[][] grid;

    public GameBoard() {
        this.length = 6;
        this.breadth = 7;
        grid = new GridPosition[length][breadth];
        for(int row = 0; row < this.length; row++)
            for(int col = 0; col < this.breadth; col++)
                grid[row][col] = new GridPosition();

    }

    public GameBoard(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
        grid = new GridPosition[length][breadth];
        for(int row = 0; row < this.length; row++)
            for(int col = 0; col < this.breadth; col++)
                grid[row][col] = new GridPosition();

    }
}
