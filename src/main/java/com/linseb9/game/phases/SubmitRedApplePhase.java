package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;

import java.util.*;

public class SubmitRedApplePhase implements Phase{
    private String gameMessage = "Non judge players, please submit your red apple";
    private final ArrayList<Player> counter;
    private Boolean phaseComplete;

    public SubmitRedApplePhase() {
        this.phaseComplete = false;
        this.counter = new ArrayList<Player>();
    }

    @Override
    public String getMessage() {
        return gameMessage;
    }

    @Override
    public Boolean isAutonomous() {
        return false;
    }

    @Override
    public void handle(Game game, GameAction action, Player player) {
        TerminalFormatting tformat = new TerminalFormatting();
        if (player.isJudge()){
            game.enqueueEvent(new GameEvent(game, action, tformat.getBlue() +"You are the judge and cannot submit an apple" + tformat.getReset(), player));
            return;
        }
        if (action.getName().equals("PLAY CARD")) {
            if (counter.contains(player)) {
                game.enqueueEvent(new GameEvent(game, action, tformat.getBlue() + "You have already submitted your card" + tformat.getReset(), player));
                return;
            }
            else {
                counter.add(player);
                List<Map.Entry<Card, Player>> subbedRedApples = game.getSubmittedRedApples();
                subbedRedApples.add(new AbstractMap.SimpleEntry<>(player.getDeck().remove(action.getCardNr()),player));

                if (counter.size() == game.getTotalPlayers() - 1) {
                    phaseComplete = true;
                    game.enqueueEvent(new GameEvent(game, action, "All players have submitted an apple", null));
                    return;
                }
                game.enqueueEvent(new GameEvent(game, action, "A player submitted a red apple", null));
                return;
            }
        }
        game.enqueueEvent(new GameEvent(game, action, "Invalid action for this phase", null));
    }

    @Override
    public Phase nextPhase() {
        return new DisplayRedApplePhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
