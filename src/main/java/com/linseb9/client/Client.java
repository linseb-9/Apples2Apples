package com.linseb9.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;
    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to server");

            // input from user
            input = new BufferedReader(new InputStreamReader(System.in));

            // send data via socket
            out = new DataOutputStream(socket.getOutputStream());

        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException io) {
            System.out.println(io);
            return;
        }

        // string to read message from user
        String line = "";

        while (!line.equals("Exit")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch (IOException io) {
                System.out.println(io);
            }
        }

        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException io) {
            System.out.println(io);
        }


    }


    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 1337);
    }
}
