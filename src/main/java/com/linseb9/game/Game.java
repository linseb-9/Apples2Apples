package com.linseb9.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numOfPlayers;
    private int numOfBots;
    private int totalPlayers;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final List<Player> players;
    private Phase currentPhase;
    private int gameCounter;
    private List<Card> greenApples;
    private List<Card> redApples;


    public enum Phase{
        SETUP,
        SHUFFLE_CARDS,
        DEAL_CARDS,
        DRAW_GREEN_APPLE,
        SUBMIT_RED_APPLE,
        JUDGE_SELECT,
    }

    public Game(int numOfPlayers, int numOfBots){
        this.numOfPlayers = numOfPlayers;
        this.numOfBots = numOfBots;
        this.totalPlayers = numOfPlayers + numOfBots;
        this.currentPhase = Phase.SETUP;
        this.gameCounter = 0;
        players = new ArrayList<Player>();
    }

    public void addEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(GameEventListener listener) {
        listeners.remove(listener);
    }

    public void addPlayers(Player player) {
        players.add(player);
    }

    public void performAction(Player player, GameAction action) {
        gameCounter++;
        phaseHandler();
        // Process game action and update state
        GameEvent event = eventHandler(action);
        //GameEvent event = new GameEvent(this, action);
        notifyListeners(event);
    }

    private void notifyListeners(GameEvent event) {
        for (GameEventListener listener : listeners) {
            listener.onGameEvent(event);
        }
    }

    private GameEvent eventHandler(GameAction action) {
        if (currentPhase == Phase.SETUP) {
            return new GameEvent(this, action, "Setup phase");
        }
        else if (currentPhase == Phase.SHUFFLE_CARDS) {
            shuffleDeck();
            return new GameEvent(this, action, "Shuffling cards");
        }
        else if (currentPhase == Phase.DEAL_CARDS) {
            dealCards((ArrayList<Player>) players);
            return new GameEvent(this, action, "Dealing cards");
        }
        else if (currentPhase == Phase.DRAW_GREEN_APPLE) {
            return new GameEvent(this, action, "Draw Green Cards");
        }
        else if (currentPhase == Phase.JUDGE_SELECT) {
            return new GameEvent(this, action, "Judge Selects Winner");
        }
        else {
            return new GameEvent(this, action, "Unknown event");
        }
    }

    private void phaseHandler(){
        switch (currentPhase) {
            case Phase.SETUP:
                if (gameCounter == totalPlayers) {
                    currentPhase = Phase.SHUFFLE_CARDS;
                    gameCounter = 0;
                }
                System.out.println("Current Phase: " + currentPhase);
                break;
            case Phase.SHUFFLE_CARDS:
                currentPhase = Phase.DEAL_CARDS;
                System.out.println("Current Phase: " + currentPhase);
                /*if (gameCounter == totalPlayers) {
                    //gameCounter = 0;
                }*/
                break;
            case Phase.DEAL_CARDS:
                if (gameCounter == totalPlayers) {
                    currentPhase = Phase.DRAW_GREEN_APPLE;
                    gameCounter = 0;
                }
                System.out.println("Current Phase: " + currentPhase);
                break;
            case Phase.DRAW_GREEN_APPLE:
                if (gameCounter == totalPlayers) {
                    currentPhase = Phase.SUBMIT_RED_APPLE;
                    gameCounter = 0;
                }
                System.out.println("Current Phase: " + currentPhase);
                break;
            case Phase.JUDGE_SELECT:
                System.out.println("Judge Select");
                break;
            default:
                System.out.print("Stay in same phase");
        }


    }

    private void initializeCards(){

    }

    private void shuffleDeck(){

    }

    private void dealCards(ArrayList<Player> players) {
        for(Player player: players) {

        }
    }
}

