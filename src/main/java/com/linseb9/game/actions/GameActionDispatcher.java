package com.linseb9.game.actions;

import com.linseb9.game.core.Game;
import com.linseb9.game.players.Player;

/**
 * The GameActionDispatcher class is responsible for dispatching the
 * GameEvents created by the players to the Game.
 */
public class GameActionDispatcher {
    private Game game;

    public GameActionDispatcher(Game game) {
        this.game = game;
    }
    // Forward the action to the Game
    public void dispatchAction(GameAction action, Player sender) {
        System.out.println("Dispatching action: " + action.getName() + " from client");


        game.performAction(sender, action);
    }
}
