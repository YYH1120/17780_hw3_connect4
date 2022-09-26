package org.game.connect4.util;

public enum TokenColor {
    RED('R'),
    BLUE('B');

    public final Character symbol;

    private TokenColor(Character symbol) {
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }
}
