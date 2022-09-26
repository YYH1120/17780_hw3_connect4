package org.game.connect4;

import org.game.connect4.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class GameGrid {
    private final int height;
    private final int width;
    private final List<List<Character>> grid = new ArrayList<>();

    public GameGrid(int height, int width) {
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
        int i, j;
        for (i = this.height-1; i >= 0; i--) {
            for (j = 0; j < this.width; j++) {
                if (i > this.grid.get(j).size()-1)
                    System.out.print(GameConstants.EMPTY_SLOT + "\t");
                else
                    System.out.print(this.grid.get(j).get(i) + "\t");
            }
            System.out.println();
        }
    }
}
