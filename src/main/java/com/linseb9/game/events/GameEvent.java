package com.linseb9.game.events;

import com.linseb9.game.actions.GameAction;

import java.util.EventObject;

public class GameEvent extends EventObject {
    private final String message;

    private final GameAction action;

    public GameEvent(Object source, GameAction action, String message) {
        super(source);
        this.action = action;
        this.message = message;
    }

    public GameAction getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }
}

