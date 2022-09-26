package org.game.connect4;

import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

public record Player(PlayerID id, String name, boolean isComputer,
                     TokenColor tokenColor) {
}