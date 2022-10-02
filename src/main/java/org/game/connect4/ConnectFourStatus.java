package org.game.connect4;

import org.game.connect4.util.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class ConnectFourStatus {
    private GameStatus gameStatus;
    private List<GridPosition> winningSequence;

    public ConnectFourStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.winningSequence = new ArrayList<>();
    }

    public ConnectFourStatus(GameStatus gameStatus, List<GridPosition> winningSequence) {
        this.gameStatus = gameStatus;
        this.winningSequence = winningSequence;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<GridPosition> getWinningSequence() {
        return winningSequence;
    }
}
