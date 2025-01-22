package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
/**
 * The different phases does exactly as their names suggest. The handle method
 * is where the logic is handled and each phase has its own implementation.
 * The method nextPhase is used by the game to move to a new phase.
 */
public class ShuffleCardsPhase implements Phase {
    private String gameMessage = "Shuffling cards";
    private Boolean phaseComplete = false;

    public ShuffleCardsPhase(){
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
        GameMechanics gameMechanics = game.getGameMechanics();
        gameMechanics.shuffleDeck(game.getGreenApples());
        gameMechanics.shuffleDeck(game.getRedApples());
        phaseComplete = true;
        game.enqueueEvent(new GameEvent(this, action, "Decks has been shuffled", null ));
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
