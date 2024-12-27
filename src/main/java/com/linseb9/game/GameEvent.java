package com.linseb9.game;

import java.util.EventObject;

public class GameEvent extends EventObject {
    private final GameAction action;

    public GameEvent(Object source, GameAction action) {
        super(source);
        this.action = action;
    }

    public GameAction getAction() {
        return action;
    }
}

