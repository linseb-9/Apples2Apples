package com.linseb9.client;

import com.linseb9.game.GameAction;
import com.linseb9.game.GameEvent;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
    private Socket socket = null;
    private BufferedReader userInput = null;
    private DataOutputStream outString = null;
    private ObjectOutputStream out = null;
    //private DataInputStream in = null;
    private ObjectInputStream in = null;
    private GameAction action;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);

            // input from user
            userInput = new BufferedReader(new InputStreamReader(System.in));

            // send string to server
            outString = new DataOutputStream(socket.getOutputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            // read data via socket
            //in = new DataInputStream(socket.getInputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connected to gameserver...");

            // new thread to get server messages
            new Thread(this::eventReceiver).start();

            // take userinput
            gameActionTransmitter();
        }
        catch (UnknownHostException u) {
            System.out.println(u);
        }
        catch (IOException io) {
            System.out.println("Constructor: " + io);
        }
        finally {
            closeConnection();
        }

    }

    private void eventReceiver() {
        boolean run = true;
        try{
            while(run) {
                GameEvent event = (GameEvent) in.readObject();
                System.out.println("Event recieved at client: " + event.getMessage());
            }
        }
        catch(IOException io) {
            System.out.println("eventReceiver: " + io);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            run = false;
        }
    }


    private void eventParser(GameEvent event){

    }

    private void gameActionTransmitter () {
        // string to read message from user
        String input = "";
        try {
            while (!input.equals("Exit")) {
                input = userInput.readLine();
                if (!verifyInput(input)){
                    System.out.println("Enter a valid digit");
                    continue;
                }
                GameAction action = new GameAction(input);
                out.writeObject(action);
                //out.writeUTF(line);
            }
        }
        catch (IOException io) {
            System.out.println("GameActionTransmitter: " + io);
        }
        finally {
            System.out.println("Dis is Wierd");
            closeConnection();
        }

    }

    private boolean verifyInput(String input) {
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void closeConnection() {
        // close the connection
        try {
            System.out.println("Closing connection and shutting down");
            userInput.close();
            out.close();
            socket.close();
            System.exit(1);
        }
        catch (IOException io) {
            System.out.println("closeConnection: " + io);
        }
    }


    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 1338);
    }
}
