package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.core.Rules;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

public class ChooseJudgePhase implements Phase{
    private String name = "Choose judge phase";
    private Boolean phaseComplete = false;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isAutonomous() {
        return true;
    }

    @Override
    public GameEvent handle(Game game, GameAction action, Player player) {
        // This is autonomous and doesn't need the input
        Rules rules = game.getRules();
        player  = rules.chooseJudge(game.getPlayers());
        phaseComplete = true;
        return new GameEvent(this, action, "Judge is player " + player.id);

    }

    @Override
    public Phase nextPhase() {
        return new DrawGreeApplePhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
