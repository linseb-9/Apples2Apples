package com.linseb9.game;

import java.util.EventObject;

public class GameEvent extends EventObject {
    private String message;
    private String name = "TestEvent";
    private final GameAction action;

    public GameEvent(Object source, GameAction action, String message) {
        super(source);
        this.action = action;
        this.message = message;
    }

    public GameAction getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}

