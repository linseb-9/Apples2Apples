package com.linseb9.client;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.events.GameEvent;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private BufferedReader userInput;
    private DataOutputStream outString;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client(String address, int port) {
        try {
            initializeConnection(address, port);
            System.out.println("Connected to gameserver...");

            startEventReceiverThread();
            startGameActionTransmitter();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error during connection setup: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void initializeConnection(String address, int port) throws IOException {
        socket = new Socket(address, port);
        userInput = new BufferedReader(new InputStreamReader(System.in));
        outString = new DataOutputStream(socket.getOutputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    private void startEventReceiverThread() {
        new Thread(this::eventReceiver).start();
    }

    private void eventReceiver() {
        try {
            while (true) {
                GameEvent event = (GameEvent) in.readObject();
                System.out.println("Game: " + event.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error receiving event: " + e.getMessage());
            closeConnection();
        } catch (ClassNotFoundException e) {
            System.out.println("Error parsing event: " + e.getMessage());
        }
    }

    private void startGameActionTransmitter() {
        try {
            sendInitialGameAction();
            transmitUserInput();
        } catch (IOException e) {
            System.out.println("Error during game action transmission: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void sendInitialGameAction() throws IOException {
        GameAction initialAction = new GameAction("JOINED GAME", 0);
        out.writeObject(initialAction);
    }

    private void transmitUserInput() throws IOException {
        String input;
        while (true) {
            input = userInput.readLine();
            if (input.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting game...");
                break;
            }

            if (!isValidInput(input)) {
                System.out.println("Invalid input. Please enter a valid digit.");
                continue;
            }

            GameAction action = new GameAction("PLAY CARD", Integer.parseInt(input));
            out.writeObject(action);
        }
    }

    private boolean isValidInput(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void closeConnection() {
        try {
            System.out.println("Closing connection and shutting down.");
            if (userInput != null) userInput.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error during connection closure: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 1338);
    }
}
