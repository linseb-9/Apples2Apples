package com.linseb9.server;

import com.linseb9.game.actions.GameActionDispatcher;
import com.linseb9.game.core.Game;
import com.linseb9.game.actions.GameAction;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.HumanPlayer;
import com.linseb9.game.players.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.EventObject;

public class ClientHandler implements Runnable {
    private final GameActionDispatcher gameActionDispatcher;
    private final Socket clientSocket;
    private ObjectInputStream dataIn = null;
    private ObjectOutputStream dataOut = null;
    private final Game game;
    private final Player player;

    public ClientHandler(Socket socket, GameActionDispatcher gameActionDispatcher, Game game, int playerId) {
        this.player = new HumanPlayer(playerId);
        this.game = game;
        this.gameActionDispatcher = gameActionDispatcher;
        this.clientSocket = socket;
        game.addPlayers(this.player);

        try{
            this.dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
            this.dataIn = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (Exception e) {
            System.out.println("Failed to initialize the clientHandler: " + e.getMessage());
            cleanup();
        }
    }


    public void run() {
        try {
            System.out.println("ClientHandler is up and running");
            while(true) {
                // Read from client
                GameAction action = (GameAction) dataIn.readObject();
                gameActionDispatcher.dispatchAction(action, player);
            }
        }
        catch(Exception e) {
            System.out.println("Something went wrong in the ClientHandler: " + e.getMessage());
            cleanup();
        }
    }
    // Forward to the clients
    public void forwardMessage(EventObject event) {
        try{
            if (event instanceof GameEvent gameEvent) {
                if (gameEvent.getTargetPlayer() == this.player || (gameEvent.getTargetPlayer() == null)) {
                    // Send data to client
                    dataOut.writeObject(event);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error while forwarding message: " + e.getMessage());
        }

    }

    public Player getPlayer() {
        return player;
    }

    private void cleanup() {
        try {
            System.out.println("Cleaning up resources for client: " + clientSocket.getInetAddress());
            if (dataIn != null) dataIn.close();
            if (dataOut != null) dataOut.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            game.removePlayers(this.player); // If you have a method to remove players
        } catch (IOException e) {
            System.err.println("Error during cleanup: " + e.getMessage());
        }
    }


}
