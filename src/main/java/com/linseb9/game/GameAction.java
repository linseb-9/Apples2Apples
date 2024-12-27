package com.linseb9.game;

public class GameAction {
    private final String name;
    private final Object data;

    public GameAction(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public void execute() {
        System.out.println("Executing action " + name);
    }
}
