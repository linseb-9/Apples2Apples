package com.linseb9.game.actions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameActionTest {


    @Test
    void TestCreateGameAction() {
        String name = "TEST ACTION";
        int cardNr = 1;
        GameAction gameAction = new GameAction(name, cardNr);
        assertNotNull(gameAction, "gameAction should not be null");
        assertEquals(gameAction.getName(), name, "The names should be the same");
        assertEquals(gameAction.getCardNr(), cardNr, "The numbers should be the same");
    }

}
