package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

public interface Phase {

    public String getMessage();

    public Boolean isAutonomous();

    public void handle(Game game, GameAction action, Player player);

    public Phase nextPhase();

    public boolean isComplete();
}
