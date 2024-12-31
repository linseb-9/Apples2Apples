package com.linseb9.game;

import com.linseb9.client.Client;
import com.linseb9.server.ClientHandler;
import com.linseb9.server.Server;

import java.util.ArrayList;

public class EventDispatcher implements GameEventListener {
    //private final Server server;
    private Game game;
    private final ArrayList<ClientHandler> clients;


    public EventDispatcher(Game game) {
        this.game = game;
        clients = new ArrayList<ClientHandler>();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        for (ClientHandler client: clients) {
                client.forwardMessage(event);
        }
        System.out.println("Event is " + event.getName());
    }

    public void dispatchAction(GameAction action, Player sender) {
        System.out.println("Dispatching action: " + action.getName() + " from client");

        // Forward the action to the Game
        game.performAction(sender, action);
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
