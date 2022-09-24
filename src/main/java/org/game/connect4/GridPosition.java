package org.game.connect4;

import org.game.connect4.util.PlayerID;

public class GridPosition {
    boolean isFilled;
    PlayerID playerId;

    public GridPosition() {
        isFilled = false;
        playerId = PlayerID.NONE;
    }
}