package org.game.connect4;

import org.game.connect4.util.GameConstants;
import org.game.connect4.util.GameMode;
import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

/**
 * ConnectFourInitializer provides different ways in which the ConnectFour game can be initialized.
 * The initializers offer game creation based on game mode and default settings.
 * This is not an exhaustive list of methods. Similar functionalities can be implemented in the client code.
 */
public class ConnectFourInitializer {

    /**
     * Constructs a new ConnectFourGame for Player Vs Player mode with default settings of token color and grid dimensions
     * @param name1 - Name of the first player
     * @param name2 - Name of the second player
     * @return an instance of ConnectFourGame initialized with the given values
     */
    public ConnectFourGameImpl initializeDefaultPlayerVsPlayer(String name1, String name2) {
        GameGrid gameGrid = new GameGrid(GameConstants.DEFAULT_HEIGHT, GameConstants.DEFAULT_WIDTH);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, name2, false, TokenColor.BLUE);
        return new ConnectFourGameImpl(gameGrid, GameMode.PLAYER_VS_PLAYER, player1, player2);
    }

    /**
     * Constructs a new ConnectFourGame for Player Vs Player mode with custom grid dimensions
     * @param height - Height of the grid
     * @param width - Width of the grid
     * @param name1 - Name of the first player
     * @param name2 - Name of the second player
     * @return an instance of ConnectFourGame initialized with the input values
     */
    public ConnectFourGameImpl initializePlayerVsPlayer(int height, int width, String name1, String name2) {
        GameGrid gameGrid = new GameGrid(height, width);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, name2, false, TokenColor.BLUE);
        return new ConnectFourGameImpl(gameGrid, GameMode.PLAYER_VS_PLAYER, player1, player2);
    }

    /**
     * Constructs a new ConnectFourGame for Player Vs Computer mode with default settings of grid dimensions
     * @param name1 - Name of the human player
     * @return an instance of ConnectFourGame initialized with the given values
     */
    public ConnectFourGameImpl initializeDefaultPlayerVsComputer(String name1) {
        GameGrid gameGrid = new GameGrid(GameConstants.DEFAULT_HEIGHT, GameConstants.DEFAULT_WIDTH);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.BLUE);
        return new ConnectFourGameImpl(gameGrid, GameMode.PLAYER_VS_COMPUTER, player1, player2);
    }

    /**
     * Constructs a new ConnectFourGame for Player Vs Computer mode with custom grid dimensions
     * @param height - Height of the grid
     * @param width - Width of the grid
     * @param name1 - Name of the human player
     * @return an instance of ConnectFourGame initialized with the input values
     */
    public ConnectFourGameImpl initializePlayerVsComputer(int height, int width, String name1) {
        GameGrid gameGrid = new GameGrid(height, width);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.BLUE);
        return new ConnectFourGameImpl(gameGrid, GameMode.PLAYER_VS_COMPUTER, player1, player2);
    }

    /**
     * Constructs a new ConnectFourGame for Computer Vs Computer mode with default settings of grid dimensions
     * @return an instance of ConnectFourGame initialized with the default values
     */
    public ConnectFourGameImpl initializeDefaultComputerVsComputer() {
        GameGrid gameGrid = new GameGrid(GameConstants.DEFAULT_HEIGHT, GameConstants.DEFAULT_WIDTH);
        Player player1 = new Player(PlayerID.PLAYER_1, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER2, true, TokenColor.BLUE);
        return new ConnectFourGameImpl(gameGrid, GameMode.COMPUTER_VS_COMPUTER, player1, player2);
    }

    /**
     * Constructs a new ConnectFourGame for Computer Vs Computer mode with custom grid dimensions
     * @param height - Height of the grid
     * @param width - Width of the grid
     * @return an instance of ConnectFourGame initialized with the input values
     */
    public ConnectFourGameImpl initializeComputerVsComputer(int height, int width) {
        GameGrid gameGrid = new GameGrid(height, width);
        Player player1 = new Player(PlayerID.PLAYER_1, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER2, true, TokenColor.BLUE);
        return new ConnectFourGameImpl(gameGrid, GameMode.COMPUTER_VS_COMPUTER, player1, player2);
    }
}
