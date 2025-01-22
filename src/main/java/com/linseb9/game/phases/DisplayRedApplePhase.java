package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;

import java.util.List;
import java.util.Map;
/**
 * The different phases does exactly as their names suggest. The handle method
 * is where the logic is handled and each phase has its own implementation.
 * The method nextPhase is used by the game to move to a new phase.
 */
public class DisplayRedApplePhase implements Phase{
    private String gameMessage = "Played red apples: ";
    private Boolean phaseComplete;

    public DisplayRedApplePhase(){
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
        game.enqueueEvent(new GameEvent(game, action, displayCards(game.getSubmittedRedApples(), tformat), null));
        phaseComplete = true;
    }

    @Override
    public Phase nextPhase() {
        return new PickWinnerPhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }

    private String displayCards(List<Map.Entry<Card, Player>> subbedRedApples, TerminalFormatting tformat) {
        StringBuilder cardDisplay = new StringBuilder();
        int index = 0;
        for (Map.Entry<Card, Player> entry : subbedRedApples) {
            Card card = entry.getKey();
            cardDisplay.append("\n").append(tformat.getRed()).append(index).append(": ").append(card.attributes()).append(tformat.getReset());
            index++;
        }
        return cardDisplay.toString();
    }

}
