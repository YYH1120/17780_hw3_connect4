package org.game.connect4.util;

public enum TokenColor {
    RED('R'),
    BLUE('B');

    private final Character symbol;

    TokenColor(Character symbol) {
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }
}
