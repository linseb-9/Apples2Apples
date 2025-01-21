package com.linseb9.server;

import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    @Test
    void testServerStarts() {
        int port = 1338;
        Thread serverThread = new Thread(() -> {
            try {
                Server server = new Server(port, null, null, null, 1);
            } catch (Exception e) {
                fail("TEST: Server failed to start: " + e.getMessage());
            }
        });

        serverThread.start();

        try {
            // Allow the server time to start
            Thread.sleep(500);

            // Verify the server is listening on the port
            try (Socket clientSocket = new Socket("localhost", port)) {
                assertTrue(clientSocket.isConnected(), "TEST: Client should successfully connect to the server.");
            }
        } catch (Exception e) {
            fail("TEST: The server did not start correctly: " + e.getMessage());
        } finally {
            serverThread.interrupt();
        }
    }
}
