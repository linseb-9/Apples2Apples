package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

public class ShuffleCardsPhase implements Phase {
    private String name = "Shuffle cards phase";
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
        gameMechanics.shuffleDeck(game.getGreenApples());
        gameMechanics.shuffleDeck(game.getRedApples());
        phaseComplete = true;
        return new GameEvent(this, action, "Decks has been shuffled" );

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
