package com.linseb9.game.core;

import com.linseb9.game.players.Player;

import java.util.ArrayList;
import java.util.Random;

public class StandardRules implements Rules {
    private final int winningCondition;
    private Random random;

    public StandardRules(int players) {
        this.winningCondition = calculateWinningCondition(players);
        this.random = new Random();
    }

    @Override
    public boolean isWinner(Player player) {
        return player.score == winningCondition;
    }

    @Override
    public void resetGame(ArrayList<Player> players) {
        validatePlayersList(players, "reset the game");
        for (Player player: players) {
            player.score = 0;
            player.judge = false;
        }
    }

    @Override
    public Player chooseJudge(ArrayList<Player> players) {
        validatePlayersList(players, "choose a judge");
        int randomIndex = random.nextInt(players.size());
        Player player = players.get(randomIndex);
        player.judge = true;
        return player;
    }

    @Override
    public void newRound(ArrayList<Player> players) {
        validatePlayersList(players, "start a new round");
        for (Player player: players) {
            player.judge = false;
        }
    }

    private void validatePlayersList(ArrayList<Player> players, String action) {
        if (players.isEmpty()) {
            throw new IllegalStateException("Cannot " + action + " with an empty players arraylist");
        }
    }
}
