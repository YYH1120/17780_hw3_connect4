package org.game.connect4;

import org.game.connect4.util.PlayerID;

public class Player {
    PlayerID id;
    String name;
    boolean isComputer;

    public Player(PlayerID id, String name, boolean isComputer) {
        this.id = id;
        this.name = name;
        this.isComputer = isComputer;
    }
}