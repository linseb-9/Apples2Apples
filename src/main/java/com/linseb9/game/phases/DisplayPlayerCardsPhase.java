package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;

import java.util.ArrayList;

public class DisplayPlayerCardsPhase implements Phase{
    private String gameMessage = "Your cards: ";
    private Boolean phaseComplete = false;

    public DisplayPlayerCardsPhase(){
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
        ArrayList<Player> players = game.getPlayers();
        for (Player plr: players) {
            if (!plr.judge) {
                game.enqueueEvent(new GameEvent(game, action, displayCards(plr, tformat), plr));
            }
        }
        phaseComplete = true;
    }

    @Override
    public Phase nextPhase() {
        return new SubmitRedApplePhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }

    private String displayCards(Player player, TerminalFormatting tformat) {
        StringBuilder cardDisplay = new StringBuilder();
        int index = 0;
        for (Card card : player.deck) {
            cardDisplay.append("\n").append(tformat.getRed()).append(index).append(": ").append(card.attributes()).append(tformat.getReset());
            index++;
        }
        return cardDisplay.toString();
    }
}
