package com.linseb9;

import com.linseb9.game.actions.GameActionDispatcher;
import com.linseb9.game.events.EventDispatcher;
import com.linseb9.game.core.Game;
import com.linseb9.game.players.BotPlayer;
import com.linseb9.server.Server;

import java.util.*;


public class Apples2Apples {
    private static final int MAX_TRIES = 5;
    private static final int MAX_PLAYERS = 8;
    private static final int MIN_PLAYERS = 4;
    private static final int MAX_BOTS = 3;
    private static final int MIN_BOTS = 0;
    private static int numOfBots;
    private static int numOfPlayers;

    private static void initializeGame(int numOfPlayers, int numOfBots) {
        Game game = new Game(numOfPlayers, numOfBots);
        EventDispatcher eventDispatcher = new EventDispatcher();
        GameActionDispatcher gameActionDispatcher = new GameActionDispatcher(game);
        game.addEventListener(eventDispatcher);
        addBots(numOfBots, game, eventDispatcher, gameActionDispatcher);
        System.out.println("Starting game server");
        Server server = new Server(1338, eventDispatcher, gameActionDispatcher, game, numOfBots);
    }

    private static void addBots(int numOfBots, Game game, EventDispatcher eventDispatcher, GameActionDispatcher gameActionDispatcher) {

        int botId = 0;
        if (numOfBots != 0) {
             for (int i =0; i < numOfBots; i++) {
                 BotPlayer bot = new BotPlayer(botId, game, eventDispatcher, gameActionDispatcher);
                 bot.run();
                 botId++;
             }
             System.out.println(numOfBots + " bots added to the game.");
         }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputCounter = 0;

        System.out.println("Welcome to Apples to Apples!");
        System.out.println("Please enter how many players that are attending, between " + MIN_PLAYERS + " - " + MAX_PLAYERS);

        while (inputCounter < MAX_TRIES) {
            try{
                numOfPlayers = scanner.nextInt();
                if (numOfPlayers > MAX_PLAYERS || numOfPlayers < MIN_PLAYERS) {
                    System.out.println("Error, Please enter a digit between " + MIN_PLAYERS + " - " + MAX_PLAYERS);
                    inputCounter++;
                    continue;
                }

                System.out.println("How many bots would you like to add to the game? Chose between " + MIN_BOTS + " - " + MAX_BOTS);
                while (inputCounter < MAX_TRIES) {
                    try {
                        numOfBots = scanner.nextInt();
                        if (numOfBots > MAX_BOTS || numOfBots < MIN_BOTS) {
                            System.out.println("Error, please enter a digit between "  + MIN_BOTS + " - " + MAX_BOTS);
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