package com.linseb9.client;


import java.io.IOException;

public class Client {
    private ConnectionManager connectionManager;

    public Client(String address, int port) {
        try {
            connectionManager = new ConnectionManager(address, port);
            System.out.println("Connected to gameserver...");

            // Start receiving events
            EventReceiver eventReceiver = new EventReceiver(connectionManager.getInputStream());
            new Thread(eventReceiver).start();

            // Start transmitting actions
            ActionTransmitter actionTransmitter = new ActionTransmitter(connectionManager.getOutputStream());
            actionTransmitter.startTransmitting();

        } catch (IOException e) {
            System.out.println("Error during connection setup: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connectionManager != null) connectionManager.close();
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 1338);
    }
}
