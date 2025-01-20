package com.linseb9.client;

import com.linseb9.game.events.GameEvent;

import java.io.IOException;
import java.io.ObjectInputStream;

public class EventReceiver implements Runnable {
    private ObjectInputStream input;
    private boolean running;

    public EventReceiver(ObjectInputStream input) {
        this.input = input;
    }

    @Override
    public void run(){
        running = true;
        try {
            while (running) {
                GameEvent event = (GameEvent) input.readObject();
                System.out.println("Game: " + event.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error receiving event: " + e.getMessage());
        }
    }

    public void close() {
        running = false;
    }

}
