package com.linseb9.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<GameEventListener> listeners = new ArrayList<>();

    public void addEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(GameEventListener listener) {
        listeners.remove(listener);
    }

    public void performAction(GameAction action) {
        // Process game action and update state
        GameEvent event = new GameEvent(this, action);
        notifyListeners(event);
    }

    private void notifyListeners(GameEvent event) {
        for (GameEventListener listener : listeners) {
            listener.onGameEvent(event);
        }
    }
}

