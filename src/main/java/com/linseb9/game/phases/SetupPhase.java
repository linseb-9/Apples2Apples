package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;

import java.util.ArrayList;

public class SetupPhase implements Phase{
    private String name = "Setup Phase";
    private final ArrayList<Player> counter;
    private Boolean phaseComplete;


    public SetupPhase() {
        this.phaseComplete = false;
        this.counter = new ArrayList<Player>();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isAutonomous() {
        return false;
    }

    @Override
    public GameEvent handle(Game game, GameAction action, Player player) {
        if(action.getName().equals("JOINED GAME")) {
            if (counter.contains(player)) {
                return new GameEvent(game, action, "Still waiting for players" );
            }
            else {
                counter.add(player);

                if (counter.size() == game.getTotalPlayers()) {
                    phaseComplete = true;
                    return new GameEvent(game, action, "Setup phase complete" );
                }
                return new GameEvent(game, action, "A new player joined the game" );
            }
        }
        return new GameEvent(game, action, "Invalid action for this phase" );
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
