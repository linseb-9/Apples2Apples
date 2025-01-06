package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.core.Rules;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

import java.util.ArrayList;

public class NextRoundPhase implements Phase{
    private String gameMessage = "Moving to next round";
    private Boolean phaseComplete;

    public NextRoundPhase(){
        this.phaseComplete = false;
    }

    @Override
    public String getMessage() {
        return gameMessage;
    }

    @Override
    public Boolean isAutonomous() {
        return true;
    }

    @Override
    public void handle(Game game, GameAction action, Player player) {
        // This is autonomous and doesn't need the input
        Rules rules = game.getRules();
        ArrayList<Player> players = game.getPlayers();
        for (Player plr: players) {
            if (rules.isWinner(plr)) {
                game.enqueueEvent(new GameEvent(game, action, "Game over, winner is player " + plr.id, null));
                game.enqueueEvent(new GameEvent(game, action, "Type 'Exit' to close the game", null));
                return;
            }
        }

        rules.newRound(players);
        game.getSubmittedRedApples().clear();
        phaseComplete = true;
        game.enqueueEvent(new GameEvent(game, action, "New round is starting", null));
    }

    @Override
    public Phase nextPhase() {
        return new DealCardPhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
