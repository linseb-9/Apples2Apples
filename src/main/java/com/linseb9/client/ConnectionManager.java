package com.linseb9.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionManager {
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;


    public ConnectionManager(String address,int port) throws IOException {
        this.socket = new Socket(address, port);
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream getOutputStream() {
        return output;
    }

    public ObjectInputStream getInputStream() {
        return input;
    }

    public void close() throws IOException {
        System.out.println("Closing connection and shutting down.");
        if (output != null) output.close();
        if (input != null) input.close();
        if (socket != null) socket.close();

    }


}
