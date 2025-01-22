package com.linseb9.client;

import com.linseb9.game.actions.GameAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

/**
 * The ActionTransmitter class is responsible for validating user input and
 *  creating and transmitting GameActions from the client to the server.
 */
public class ActionTransmitter {
    private final ObjectOutputStream output;
    private final BufferedReader userInput;

    public ActionTransmitter(ObjectOutputStream output) throws IOException {
        this.output = output;
        this.userInput = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startTransmitting() throws IOException {
        sendInitialGameAction();
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
            output.writeObject(action);
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

    private void sendInitialGameAction() throws IOException {
        GameAction initialAction = new GameAction("JOINED GAME", 0);
        output.writeObject(initialAction);
    }
}
