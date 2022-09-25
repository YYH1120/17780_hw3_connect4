package org.game.connect4;

import org.game.connect4.util.PlayerID;

public class Player {
    int id;
    String name;
    boolean isComputer;

    public Player(int id, String name, boolean isComputer) {
        this.id = id;
        this.name = name;
        this.isComputer = isComputer;
    }
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void SetPerson(){
        this.isComputer = false;
    }

    public void SetComputer(){
        this.isComputer = true;
    }

    public int getId() {
        return id;
    }
}