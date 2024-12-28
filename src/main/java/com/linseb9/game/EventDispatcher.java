package com.linseb9.game;

import com.linseb9.client.Client;
import com.linseb9.server.Server;

public class EventDispatcher implements GameEventListener {
    private final Server server;
    private final Client client;

    public EventDispatcher(Server server, Client client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void onGameEvent(GameEvent event) {
        System.out.println("Event is " + event);
    }
}
