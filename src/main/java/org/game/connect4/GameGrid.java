package org.game.connect4;

import org.game.connect4.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * GameGrid represents the main grid of the ConnectFour game with slots for a token
 */
public class GameGrid {
    /**
     * Height of the grid
     */
    private final int height;
    /**
     * Width of the grid
     */
    private final int width;
    /**
     * The grid is represented as a two-dimensional list of single byte characters indicating the color of a token
     */
    private final List<List<Character>> grid = new ArrayList<>();

    /**
     * Constructs a game grid with the specified height and width
     * @param height the height of the grid
     * @param width the width of the grid
     */
    public GameGrid(int height, int width) {
        this.height = height;
        this.width = width;
        /* creating a two dimensional list */
        for (int i = 0; i < this.width; i++){
            List<Character> tmp = new ArrayList<>();
            grid.add(tmp);
        }
    }

    /**
     * Get the grid of the ConnectFour game
     * @return a two-dimensional list representing the grid
     */
    public List<List<Character>> getGrid() {
        return grid;
    }

    /**
     * Get the height of the ConnectFour grid
     * @return height of the grid
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the ConnectFour grid
     * @return width of the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * Displays the current state of the ConnectFour grid. Empty slots are represented as '.' in the output.
     * This representation is helpful while creating text-based clients for the ConnectFour Game.
     * Similar implementation can be used to display the grid in different ways.
     */
    public void displayGrid() {
        int i, j;
        for (i = getHeight()-1; i >= 0; i--) {
            for (j = 0; j < getWidth(); j++) {
                if (i > getGrid().get(j).size()-1)
                    System.out.print(GameConstants.EMPTY_SLOT + "\t");
                else
                    System.out.print(getGrid().get(j).get(i) + "\t");
            }
            System.out.println();
        }
    }
}
