package com.linseb9.client;

import java.io.IOException;

/**
 * The Client class is the entry point for the client side of the game. It is
 * responsible to initialize the connectionManager, EventReceiver and ActionTransmitter
 */
public class Client {
    private ConnectionManager connectionManager;
    private EventReceiver eventReceiver;
    private ActionTransmitter actionTransmitter;

    public Client(String address, int port) {
        try{
            connectionManager = new ConnectionManager(address, port);
            System.out.println("Connected to gameserver...");
        } catch (IOException e) {
            System.out.println("Failed to connect to server: " + e.getMessage());
        }

    }

    public void startEventReceiver() {
        try {
            eventReceiver = new EventReceiver(connectionManager.getInputStream());
            new Thread(eventReceiver).start();
            System.out.println("EventReceiver started.");
        } catch (IOException e) {
            System.out.println("Failed to start EventReceiver: " + e.getMessage());
        }
    }

    public void startActionTransmitter() {
        try {
            actionTransmitter = new ActionTransmitter(connectionManager.getOutputStream());
            actionTransmitter.startTransmitting();
            System.out.println("ActionTransmitter started.");
        } catch (IOException e) {
            System.out.println("Failed to start ActionTransmitter: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connectionManager != null) connectionManager.close();
            System.out.println("Client connection closed.");
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 1338);

        client.startEventReceiver();
        client.startActionTransmitter();

        Runtime.getRuntime().addShutdownHook(new Thread(client::close));

    }
}
