package org.game.connect4;

import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

/**
 * A record to store a player's details
 * @param id indicates whether a player is player 1 or 2
 * @param name save the name of a player
 * @param isComputer indicates whether a player is a computer or human. True represents computer, False represents human
 * @param tokenColor saves the token color for a player
 */
public record Player(PlayerID id, String name, boolean isComputer,
                     TokenColor tokenColor) {
}