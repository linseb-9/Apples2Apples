package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

public class DrawGreeApplePhase implements Phase {
    private String name = "Draw green apple phase";
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
        GameMechanics gameMechanics = game.getGameMechanics();
        Card greenApple = gameMechanics.drawACard(game.getGreenApples());
        phaseComplete = true;
        return new GameEvent(this, action, "Green Apple: " + greenApple.attributes());

    }

    @Override
    public Phase nextPhase() {
        return new SubmitRedApplePhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
