package org.game.connect4.util;

public enum PlayerID {
    PLAYER_1(1),
    PLAYER_2(2);

    private final int id;

    private PlayerID(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
