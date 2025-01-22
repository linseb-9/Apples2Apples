package com.linseb9.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The ConnectionManager is responsible for the connection from the Client side to the server.
 */
public class ConnectionManager {
    private final Socket socket;
    private  ObjectInputStream input = null;
    private  ObjectOutputStream output = null;


    public ConnectionManager(String address,int port) throws IOException {
        this.socket = new Socket(address, port);
    }

    public ObjectOutputStream getOutputStream() throws IOException {
        if (output == null) {
            try {
                output = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                throw new IOException("Could not start the object output stream: " + e.getMessage(), e);
            }
        }
        return output;
    }

    public ObjectInputStream getInputStream() throws IOException {
        if (input == null) {
            try {
                input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new IOException("Could not start the object intput stream: " + e.getMessage(), e);
            }
        }
        return input;
    }

    public void close() throws IOException {
        System.out.println("Closing connection and shutting down.");
        if (output != null) output.close();
        if (input != null) input.close();
        if (socket != null) socket.close();

    }


}
