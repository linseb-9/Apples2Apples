package com.linseb9.server;

import com.linseb9.game.EventDispatcher;
import com.linseb9.game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private EventDispatcher dispatcher;
    private List<ClientHandler> clients;
    private Game game;

    public Server(int port, EventDispatcher dispatcher, Game game) {
        try {
            clients = new ArrayList<ClientHandler>();
            // Initialize the game and dispatcher
            //Game game = new Game();
            //game.initialize(2, 1); // Example: 2 players, 1 bot
            this.dispatcher = dispatcher;
            this.game = game;

            // Set up the server
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            System.out.println("Waiting for clients to connect...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server: Client connected: " + clientSocket.getInetAddress());

                // Create a new ClientHandler for the client
                ClientHandler clientHandler = new ClientHandler(clientSocket, dispatcher, game, clients.size());
                clients.add(clientHandler);
                dispatcher.addClient(clientHandler);
                //sendMessage("Server: Connected to the game server" , clientHandler);
                //broadcastMessage("Server: A new player joined the server!");
                //dispatcher.addClient(clientHandler); // Register the client with the dispatcher
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    /*private void broadcastMessage(String message) {
        for (ClientHandler client: clients) {
            client.forwardMessage(message);
        }
    }

    private void sendMessage(String message, ClientHandler client) {
        client.forwardMessage(message);
    }
    */


    public static void main(String[] args) {
        //new Server(1338);
    }
}
