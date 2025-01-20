package com.linseb9.client;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientTest {


    @Test
    void TestCreateClient() {
        Client client = new Client("127.0.0.1", 1338);
        assertNotNull(client, "Client should exists");
    }

    @Test
    void TestConnectClientToServer() throws IOException {

        int port = 1338;
        try (ServerSocket mockServer = new ServerSocket(port)) {

            Client client = new Client("127.0.0.1", port);

            assertNotNull(client, "Client should exist and connect to the server.");
        }
    }
}