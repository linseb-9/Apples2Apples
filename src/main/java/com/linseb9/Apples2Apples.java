package com.linseb9;

import com.linseb9.game.EventDispatcher;
import com.linseb9.game.Game;
import com.linseb9.server.Server;

import java.util.*;


public class Apples2Apples {
    private static final int MAX_TRIES = 5;
    private static final int MAX_PLAYERS = 4;
    private static final int MIN_PLAYERS = 1;
    private static final int MAX_BOTS = 3;
    private static final int MIN_BOTS = 0;
    private static int numOfBots;
    private static int numOfPlayers;

     private static void initializeGame(int numOfPlayers, int numOfBots) {
         Game game = new Game(numOfPlayers, numOfBots);
         EventDispatcher dispatcher = new EventDispatcher(game);
         game.addEventListener(dispatcher);
         System.out.println("Starting game server");
        Server server = new Server(1338, dispatcher, game);


    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputCounter = 0;

        System.out.println("Welcome to Apples to Apples!");
        System.out.println("Please enter how many players that are attending, between 1-4");

        while (inputCounter < MAX_TRIES) {
            try{
                numOfPlayers = scanner.nextInt();
                if (numOfPlayers > MAX_PLAYERS || numOfPlayers < MIN_PLAYERS) {
                    System.out.println("Error, Please enter a digit between 1-4");
                    inputCounter++;
                    continue;
                }

                System.out.println("How many bots would you like to add to the game? Chose between 0-3");
                while (inputCounter < MAX_TRIES) {
                    try {
                        numOfBots = scanner.nextInt();
                        if (numOfBots > MAX_BOTS || numOfBots < MIN_BOTS) {
                            System.out.println("Error, please enter a digit between 0-3");
                            inputCounter++;
                            continue;
                        }
                        Apples2Apples start = new Apples2Apples();
                        initializeGame(numOfPlayers, numOfBots);
                        return;
                    }
                    catch(NoSuchElementException e) {
                        System.out.println("Please enter a digit");
                        inputCounter++;
                        scanner.nextLine();
                    }
                }

            }
            catch(NoSuchElementException e) {
                System.out.println("Please enter a digit");
                inputCounter++;
                scanner.nextLine();
            }

        }
        System.out.println("To many attempts were made, program terminating.");
        System.exit(1);
        //Server server = new Server(1338);
    }
}