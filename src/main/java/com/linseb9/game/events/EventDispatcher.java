package com.linseb9.game.events;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.players.BotPlayer;
import com.linseb9.game.players.Player;
import com.linseb9.game.core.Game;
import com.linseb9.server.ClientHandler;

import java.util.ArrayList;

public class EventDispatcher implements GameEventListener {
    //private final Server server;
    private final Game game;
    private final ArrayList<ClientHandler> clients;
    private final ArrayList<BotPlayer> botPlayers;


    public EventDispatcher(Game game) {
        this.game = game;
        this.clients = new ArrayList<ClientHandler>();
        this.botPlayers = new ArrayList<BotPlayer>();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        for (ClientHandler client: clients) {
                client.forwardMessage(event);
        }

        for (BotPlayer bot: botPlayers) {
            bot.eventReader(event);
        }
        System.out.println("Event: " + event.getMessage());
    }

    public void dispatchAction(GameAction action, Player sender) {
        System.out.println("Dispatching action: " + action.getName() + " from client");

        // Forward the action to the Game
        game.performAction(sender, action);
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
