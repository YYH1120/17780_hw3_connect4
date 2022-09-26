package org.game.connect4;

import org.game.connect4.util.PlayerID;
import org.game.connect4.util.TokenColor;

public class Player {
    private PlayerID id;
    private String name;
    private boolean isComputer;
    private TokenColor tokenColor;

    public Player(PlayerID id, String name, boolean isComputer, TokenColor tokenColor) {
        this.id = id;
        this.name = name;
        this.isComputer = isComputer;
        this.tokenColor = tokenColor;
    }
    public Player(PlayerID id, String name, TokenColor tokenColor) {
        this.id = id;
        this.name = name;
        this.tokenColor = tokenColor;
    }

    public void SetPerson(){
        this.isComputer = false;
    }

    public void SetComputer(){
        this.isComputer = true;
    }

    public PlayerID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public TokenColor getTokenColor() {
        return tokenColor;
    }
}