package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

import java.util.ArrayList;

public class SubmitRedApplePhase implements Phase{
    private String name = "Submit Red Apple phase";
    private final ArrayList<Player> counter;
    private Boolean phaseComplete;

    public SubmitRedApplePhase() {
        this.phaseComplete = false;
        this.counter = new ArrayList<Player>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isAutonomous() {
        return false;
    }

    @Override
    public GameEvent handle(Game game, GameAction action, Player player) {
        if (action.getName().equals("A")) {
            if (counter.contains(player)) {
                return new GameEvent(this, action, "Waiting for all players to submit");
            }
            else {
                counter.add(player);

                if (counter.size() == game.getTotalPlayers()) {
                    phaseComplete = true;
                    return new GameEvent(game, action, "Setup phase complete" );
                }
                return new GameEvent(game, action, "A player submitted a red apple" );
            }
        }
        return new GameEvent(game, action, "Invalid action for this phase" );
    }

    @Override
    public Phase nextPhase() {
        return new PickWinnerPhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
