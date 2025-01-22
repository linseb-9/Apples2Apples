package com.linseb9.game.events;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.players.Player;

import java.util.EventObject;

/**
 * The GameEvent class is used by the games different phases. GameEvents are
 * created in the phases based of the users GameAction inputs. The source and
 * targetPlayer are important parts to send GameEvents to the correct receiver.
 */
public class GameEvent extends EventObject {
    private final String message;
    private final GameAction action;
    private final Player targetPlayer;

    public GameEvent(Object source, GameAction action, String message, Player targetPlayer) {
        super(source);
        this.action = action;
        this.message = message;
        this.targetPlayer = targetPlayer;
    }

    public GameAction getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }

    public Player getTargetPlayer(){
        return targetPlayer;
    }
}

