package org.game.connect4;

public class GameBoard {
    int height;
    int width;
    char[][] grid;

    public GameBoard() {
        this.height = 6;
        this.width = 7;
        grid = new char[height][width];
        for(int row = 0; row < this.height; row++)
            for(int col = 0; col < this.width; col++)
                grid[row][col] = '_';

    }

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new char[height][width];
        for(int row = 0; row < this.height; row++)
            for(int col = 0; col < this.width; col++)
                grid[row][col] = '_';

    }
}
