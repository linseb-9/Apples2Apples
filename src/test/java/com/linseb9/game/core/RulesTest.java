package com.linseb9.game.core;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RulesTest {


    @Test
    void TestWinningCondition() {
        for (int i = 0; i < 5; i++) {
            Game game = new Game(i+4,0);
            Rules rules = game.getRules();
            assertEquals(8 - i, rules.calculateWinningCondition(game.getTotalPlayers()));
        }
    }
}
