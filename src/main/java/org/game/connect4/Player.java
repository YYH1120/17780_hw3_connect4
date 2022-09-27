package org.game.connect4;

import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

/**
 * Player class is used to store a player's details
 */
public class Player {
    /**
     * Indicates whether a player is player 1 or 2
     */
    PlayerID id;
    /**
     * Stores the name of a player
     */
    String name;
    /**
     * Indicates whether a player is a computer or human. True represents computer, False represents human
     */
    boolean isComputer;
    /**
     * Saves the token color for a player
     */
    TokenColor tokenColor;

    public Player(PlayerID id, String name, boolean isComputer, TokenColor tokenColor) {
        this.id = id;
        this.name = name;
        this.isComputer = isComputer;
        this.tokenColor = tokenColor;
    }

    /**
     * Get the id of a player
     * @return id of a player
     */
    public PlayerID getId() {
        return id;
    }

    /**
     * Get the name of the player
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get if the player is computer not. True indicates computer, False indicates human
     * @return if player is computer or not
     */
    public boolean isComputer() {
        return isComputer;
    }

    /**
     * Get color of token for a player
     * @return color of the token of a player
     */
    public TokenColor getTokenColor() {
        return tokenColor;
    }
}