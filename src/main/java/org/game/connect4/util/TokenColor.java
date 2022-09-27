package org.game.connect4.util;

/**
 * TokenColor enum enumerates the color of the two possible tokens in the ConnectFour game
 */
public enum TokenColor {
    /**
     * By default, player 1 is assigned a RED token with the symbol 'R'
     */
    RED('R'),
    /**
     * By default, player 2 is assigned a BLUE token with the symbol 'B'
     */
    BLUE('B');

    private final Character symbol;

    /**
     * Constructs a TokenColor with new color symbol
     * @param symbol - Character to represent a color
     */
    TokenColor(Character symbol) {
        this.symbol = symbol;
    }

    /**
     * Get the symbol associated with a Token
     * @return symbol of the color of the Token
     */
    public Character getSymbol() {
        return symbol;
    }
}
