package com.linseb9.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for clients to connect..");

            //String line = "";

            while (true){
                socket = server.accept();
                System.out.println("A client accepted");
                new Thread(() -> {
                    try {
                        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                        String line = "";
                        line = in.readUTF();
                        System.out.println(line);
                    }
                    catch (IOException io) {
                        System.out.println(io);

                    }
                }).start();
            }
            //System.out.println("Closing connection");
            //socket.close();
            //in.close();
        }
        catch (IOException io) {
            System.out.println(io);
        }
    }

public static void main(String args[]) {
    Server server = new Server(1337);
}
}
