package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;

import java.util.ArrayList;

public class SetupPhase implements Phase{
    private String gameMessage = "Setting up game";
    private final ArrayList<Player> counter = new ArrayList<Player>();
    private Boolean phaseComplete;


    public SetupPhase() {
        this.phaseComplete = false;

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
        if(action.getName().equals("JOINED GAME")) {
            if (counter.contains(player)) {
                game.enqueueEvent(new GameEvent(game, action, "Still waiting for players" , null));
                return;
            }
            else {
                counter.add(player);

                if (counter.size() == game.getTotalPlayers()) {
                    phaseComplete = true;
                    game.enqueueEvent(new GameEvent(game, action, "A new player joined the game, player: " + player.getId(), null));
                    game.enqueueEvent(new GameEvent(game, action, tformat.getBlue() + "Welcome, you are player " + player.getId() + tformat.getReset(), player));
                    game.enqueueEvent(new GameEvent(game, action, "Setup phase complete", null));
                    return;
                }
                game.enqueueEvent(new GameEvent(game, action, "A new player joined the game, player: " + player.getId(), null));
                game.enqueueEvent(new GameEvent(game, action, tformat.getBlue() +"Welcome, you are player " + player.getId() + tformat.getReset(), player));
                return;
            }
        }
        game.enqueueEvent(new GameEvent(game, action, "Invalid action for this phase", null ));
    }

    @Override
    public Phase nextPhase() {
        return new ShuffleCardsPhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
