package com.linseb9.server;

import com.linseb9.game.actions.GameActionDispatcher;
import com.linseb9.game.events.EventDispatcher;
import com.linseb9.game.core.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class is responsible to host the game. It is run from the Apples2Apples
 * class at start up.
 */
public class Server {
    private EventDispatcher eventDispatcher;
    private GameActionDispatcher gameActionDispatcher;
    private ServerSocket serverSocket;
    private boolean running;

    public Server(int port, EventDispatcher eventDispatcher, GameActionDispatcher gameActionDispatcher, Game game, int playerId) {
        this.eventDispatcher = eventDispatcher;
        this.gameActionDispatcher = gameActionDispatcher;
        this.running = true;

        try {
            // Set up the server
            serverSocket = new ServerSocket(port);
            System.out.println("Server: Started on port " + port);
            System.out.println("Server: Waiting for clients to connect...");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Server shutting down..");
                closeServer();
            }));
            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server: Client connected: " + clientSocket.getInetAddress());

                // Create a new ClientHandler for the client
                ClientHandler clientHandler = new ClientHandler(clientSocket, gameActionDispatcher, game, playerId);
                eventDispatcher.addClient(clientHandler);

                new Thread(clientHandler).start();
                playerId++;
            }
        } catch (IOException e) {
            System.err.println("Server: Server error: " + e.getMessage());
        } finally {
            closeServer();
        }
    }

    private void closeServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("Server: Server shutting down!");
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Server: Failed to shut down server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //new Server(1338);
    }
}
