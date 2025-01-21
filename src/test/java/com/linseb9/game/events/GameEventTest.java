package com.linseb9.game.events;

import com.linseb9.game.actions.GameAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameEventTest {

    @Test
    void TestCreateGameEvent() {
        GameAction gameAction = new GameAction("TEST ACTION", 0);
        GameEvent gameEvent = new GameEvent(this, gameAction, null, null);
        assertNotNull(gameEvent, "GameEvent should not be null");
    }
}
