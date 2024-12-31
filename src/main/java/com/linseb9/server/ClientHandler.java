package com.linseb9.server;

import com.linseb9.game.EventDispatcher;
import com.linseb9.game.Game;
import com.linseb9.game.GameAction;
import com.linseb9.game.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.EventObject;

public class ClientHandler implements Runnable {
    private EventDispatcher dispatcher;
    private Socket clientSocket;
    //private DataInputStream dataIn;
    private ObjectInputStream dataIn = null;
    //private DataOutputStream dataOut;
    private ObjectOutputStream dataOut = null;
    private Game game;
    private Player player;

    public ClientHandler(Socket socket, EventDispatcher dispatcher, Game game, int playerId) {
        this.player = new Player(playerId);

        try{
            this.game = game;
            this.dispatcher = dispatcher;
            this.clientSocket = socket;
            //this.dataOut = new DataOutputStream(clientSocket.getOutputStream());
            this.dataOut = new ObjectOutputStream(clientSocket.getOutputStream());
            //this.dataIn = new DataInputStream(clientSocket.getInputStream());
            this.dataIn = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (Exception e) {
            System.out.println("Failed to initialize the clientHandler: " + e);
            System.exit(1);
        }
    }


    public void run() {
        try {
            System.out.println("ClientHandler is up and running");
            while(true) {
                // Read from client
                GameAction action = (GameAction) dataIn.readObject();
                //String message = dataIn.readUTF();
                //System.out.println("Player:" + message);
                dispatcher.dispatchAction(action, player);
            }
        }
        catch(Exception e) {
            System.out.println("Something went wrong in the ClientHandler: " + e);
            System.exit(1);
        }
    }

    public void forwardMessage(EventObject event) {
        try{
            // Send data to client
            dataOut.writeObject(event);
        }
        catch(Exception e) {
            System.out.println("Error while forwarding message: " + e);
        }

    }

    public Player getPlayer() {
        return player;
    }


}
