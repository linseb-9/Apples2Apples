package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.core.GameMechanics;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;
/**
 * The different phases does exactly as their names suggest. The handle method
 * is where the logic is handled and each phase has its own implementation.
 * The method nextPhase is used by the game to move to a new phase.
 */
public class DrawGreeApplePhase implements Phase {
    private String gameMessage = "Choosing a green apple";
    private Boolean phaseComplete;

    public DrawGreeApplePhase(){
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
        GameMechanics gameMechanics = game.getGameMechanics();
        Card greenApple = gameMechanics.drawACard(game.getGreenApples());
        phaseComplete = true;
        game.enqueueEvent(new GameEvent(this, action, tformat.getGreen() + "Green apple: " + greenApple.attributes() + tformat.getReset(), null));

    }

    @Override
    public Phase nextPhase() {
        return new DisplayPlayerCardsPhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
