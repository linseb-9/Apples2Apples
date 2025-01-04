package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

import java.util.ArrayList;

public class DealCardPhase implements Phase{
    private String name = "Deal cards phase";
    private Boolean phaseComplete;

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
        GameMechanics gameMechanics = game.getGameMechanics();
        gameMechanics.dealCards(game.getPlayers(), game.getRedApples());
        phaseComplete = true;
        return new GameEvent(this, action, "Cards have been dealt");
    }

    @Override
    public Phase nextPhase() {
        return new ChooseJudgePhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
