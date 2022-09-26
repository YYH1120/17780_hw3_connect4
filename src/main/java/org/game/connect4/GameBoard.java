package org.game.connect4;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private int height;
    private int width;
    private List<List<Character>> grid = new ArrayList<>();


    public GameBoard() {
        this.height = 6;
        this.width = 7;
        for (int i = 0; i < this.width; i++){
            List<Character> tmp = new ArrayList<>();
            grid.add(tmp);
        }

//        this.grid = new char[height][width];
//        for(int row = 0; row < this.height; row++)
//            for(int col = 0; col < this.width; col++)
//                grid[row][col] = '_';

    }

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
        for (int i = 0; i < this.width; i++){
            List<Character> tmp = new ArrayList<>();
            grid.add(tmp);
        }

    }

    public List<List<Character>> getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void displayGrid() {
        int i,j;
        for (i = this.height-1; i >= 0; i--) {
            for (j = 0; j < this.width; j++) {
                if (i > this.grid.get(j).size()-1)
                    System.out.print(".\t");
                else
                    System.out.print(this.grid.get(j).get(i) + "\t");
            }
            System.out.println();
        }
    }
}
