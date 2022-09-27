package org.game.connect4.util;

/**
 * PlayerID enum enumerates the id of the two possible players in the ConnectFour game
 */
public enum PlayerID {
    /**
     * id 1 is associated with player 1
     */
    PLAYER_1(1),
    /**
     * id 2 is associated with player 2
     */
    PLAYER_2(2);

    private final int id;

    /**
     * Constructs a PlayerID with new id
     * @param id - integer id
     */
    PlayerID(int id){
        this.id = id;
    }

    /**
     * Get the id associated with a player
     * @return integer id of a player
     */
    public int getId() {
        return id;
    }
}
