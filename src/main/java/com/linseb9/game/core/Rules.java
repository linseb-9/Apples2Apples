package com.linseb9.game.core;

import com.linseb9.game.players.Player;

import java.util.ArrayList;

public interface Rules {
    public boolean isWinner(Player player);
    public Player chooseJudge(ArrayList<Player> players);
    public void newRound(ArrayList<Player> players);
    public void resetGame(ArrayList<Player> players);
    default int calculateWinningCondition(int nrOfPlayers) {
        return switch (nrOfPlayers) {
            case 4 -> 2;
            case 5 -> 7;
            case 6 -> 6;
            case 7 -> 5;
            case 8 -> 4;
            default -> 0;
        };
    }


}
