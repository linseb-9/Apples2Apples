package com.linseb9.game;

import java.io.Serializable;

public class GameAction implements Serializable {
    private final String name;
    //private final Object data;

    public GameAction(String name) {
        this.name = name;
        //this.data = data;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("Executing action " + name);
    }
}
