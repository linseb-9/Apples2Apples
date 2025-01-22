package com.linseb9.game.actions;

import java.io.Serializable;

/**
 * The GameAction class is used to move the game forward. Each input from a player
 * is generated to a GameAction that is sent to the Game.
 */
public class GameAction implements Serializable {
    private final String name;
    private final int cardNr;


    public GameAction(String name, int cardNr) {
        this.name = name;
        this.cardNr = cardNr;

    }

    public String getName() {
        return name;
    }

    public int getCardNr() {
        return cardNr;
    }

}
