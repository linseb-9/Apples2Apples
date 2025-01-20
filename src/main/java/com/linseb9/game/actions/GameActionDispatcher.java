package com.linseb9.game.actions;

import com.linseb9.game.core.Game;
import com.linseb9.game.players.Player;

public class GameActionDispatcher {
    private Game game;

    public GameActionDispatcher(Game game) {
        this.game = game;
    }

    public void dispatchAction(GameAction action, Player sender) {
        System.out.println("Dispatching action: " + action.getName() + " from client");

        // Forward the action to the Game
        game.performAction(sender, action);
    }
}
