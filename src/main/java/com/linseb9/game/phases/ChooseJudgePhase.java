package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.core.Rules;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;

public class ChooseJudgePhase implements Phase{
    private String gameMessage = "Choosing judge";
    private Boolean phaseComplete;

    public ChooseJudgePhase() {
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
        TerminalFormatting tformat = new TerminalFormatting();
        Rules rules = game.getRules();
        player  = rules.chooseJudge(game.getPlayers());
        phaseComplete = true;
        game.enqueueEvent(new GameEvent(this, action, "Judge is player " + player.id, null));
        game.enqueueEvent(new GameEvent(this, action, tformat.getBlue() + "You are the judge " +tformat.getReset(), player));

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
