package com.linseb9.game.events;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.players.BotPlayer;
import com.linseb9.game.players.Player;
import com.linseb9.game.core.Game;
import com.linseb9.server.ClientHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class EventDispatcher implements GameEventListener {
    //private final Server server;

    private final ArrayList<ClientHandler> clients;
    private final ArrayList<BotPlayer> botPlayers;
    private Queue<GameEvent> eventQueue;


    public EventDispatcher() {
        this.clients = new ArrayList<ClientHandler>();
        this.botPlayers = new ArrayList<BotPlayer>();
        this.eventQueue = new LinkedList<>();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        enqueueEvent(event);
        dispatchQueuedEvents();
    }

    public void enqueueEvent(GameEvent event) {
        eventQueue.add(event);
    }

    private void dispatchQueuedEvents() {
        while (!eventQueue.isEmpty()) {
            GameEvent event = eventQueue.poll();
            notifyClients(event);
        }
    }

    private void notifyClients(GameEvent event) {
        for (ClientHandler client: clients) {
            client.forwardMessage(event);
        }

        for (BotPlayer bot: botPlayers) {
            bot.eventReader(event);
        }
        System.out.println("Event: " + event.getMessage());
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void addClient(BotPlayer bot) {
        botPlayers.add(bot);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
