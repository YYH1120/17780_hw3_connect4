package org.game.connect4;

import org.game.connect4.util.GameConstants;
import org.game.connect4.util.GameMode;
import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

public class ConnectFourInitializer {

    public ConnectFour initializeDefaultPlayerVsPlayer(String name1, String name2) {
        GameGrid gameGrid = new GameGrid(GameConstants.DEFAULT_HEIGHT, GameConstants.DEFAULT_WIDTH);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, name2, false, TokenColor.BLUE);
        return new ConnectFour(gameGrid, GameMode.PLAYER_VS_PLAYER, player1, player2);
    }

    public ConnectFour initializePlayerVsPlayer(int height, int width, String name1, String name2) {
        GameGrid gameGrid = new GameGrid(height, width);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, name2, false, TokenColor.BLUE);
        return new ConnectFour(gameGrid, GameMode.PLAYER_VS_PLAYER, player1, player2);
    }

    public ConnectFour initializeDefaultPlayerVsComputer(String name1) {
        GameGrid gameGrid = new GameGrid(GameConstants.DEFAULT_HEIGHT, GameConstants.DEFAULT_WIDTH);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.BLUE);
        return new ConnectFour(gameGrid, GameMode.PLAYER_VS_COMPUTER, player1, player2);
    }

    public ConnectFour initializePlayerVsComputer(int height, int width, String name1) {
        GameGrid gameGrid = new GameGrid(height, width);
        Player player1 = new Player(PlayerID.PLAYER_1, name1, false, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.BLUE);
        return new ConnectFour(gameGrid, GameMode.PLAYER_VS_COMPUTER, player1, player2);
    }

    public ConnectFour initializeDefaultComputerVsComputer() {
        GameGrid gameGrid = new GameGrid(GameConstants.DEFAULT_HEIGHT, GameConstants.DEFAULT_WIDTH);
        Player player1 = new Player(PlayerID.PLAYER_1, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER2, true, TokenColor.BLUE);
        return new ConnectFour(gameGrid, GameMode.COMPUTER_VS_COMPUTER, player1, player2);
    }

    public ConnectFour initializeComputerVsComputer(int height, int width) {
        GameGrid gameGrid = new GameGrid(height, width);
        Player player1 = new Player(PlayerID.PLAYER_1, GameConstants.DEFAULT_COMPUTER1, true, TokenColor.RED);
        Player player2 = new Player(PlayerID.PLAYER_2, GameConstants.DEFAULT_COMPUTER2, true, TokenColor.BLUE);
        return new ConnectFour(gameGrid, GameMode.COMPUTER_VS_COMPUTER, player1, player2);
    }
}
