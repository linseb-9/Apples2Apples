package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

public class PickWinnerPhase implements Phase{
    private String name = "Pick winner phase";
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

        return null;
    }

    @Override
    public Phase nextPhase() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
