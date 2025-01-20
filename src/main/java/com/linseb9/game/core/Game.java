package com.linseb9.game.core;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.cards.CardBuilder;
import com.linseb9.game.events.EventDispatcher;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.events.GameEventListener;
import com.linseb9.game.phases.Phase;
import com.linseb9.game.phases.SetupPhase;
import com.linseb9.game.players.BotPlayer;
import com.linseb9.game.players.Player;

import java.util.*;

public class Game {
    private int numOfPlayers;
    private int numOfBots;
    private int totalPlayers;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final ArrayList<Player> players;
    private Phase currentPhase;
    private CardBuilder cardBuilder;
    private ArrayList<Card> greenApples;
    private ArrayList<Card> redApples;
    private List<Map.Entry<Card, Player>> submittedRedApples;
    private Rules rules;
    private Card currentGreenApple;
    private GameMechanics gameMechanics;





    public Game(int numOfPlayers, int numOfBots){
        this.numOfPlayers = numOfPlayers;
        this.numOfBots = numOfBots;
        this.totalPlayers = numOfPlayers + numOfBots;
        this.currentPhase = new SetupPhase();
        this.players = new ArrayList<Player>();
        this.gameMechanics = new GameMechanics();
        this.submittedRedApples = new ArrayList<>();
        initializeGame();
    }

    private void initializeGame() {
        rules = new StandardRules(totalPlayers);
        cardBuilder = new CardBuilder();
        greenApples = cardBuilder.buildGreenApples();
        redApples = cardBuilder.buildRedApples();

    }


    public void performAction(Player player, GameAction action) {
        // Process the action in the current phase
        processActionInPhase(player, action);

        //dispatchQueuedEvents();

        // Check if the phase is complete and transition if necessary
        while (currentPhase.isComplete()) {
            transitionToNextPhase();

            // Handle autonomous phases
            if (currentPhase.isAutonomous()) {
                processAutonomousPhase();
            }

            //dispatchQueuedEvents();
        }
    }

    private void processActionInPhase(Player player, GameAction action) {
        currentPhase.handle(this, action, player);
    }

    private void transitionToNextPhase() {
        currentPhase = currentPhase.nextPhase();
        GameEvent event = new GameEvent(currentPhase, null,  currentPhase.getMessage(), null);
        enqueueEvent(event);
    }

    private void processAutonomousPhase() {
        currentPhase.handle(this, null, null);

    }

    public void enqueueEvent(GameEvent event) {
        for (GameEventListener listener : listeners) {
            listener.onGameEvent(event);
        }
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

    public void removePlayers(Player player) {
        players.remove(player);
    }

    public GameMechanics getGameMechanics() {
        return gameMechanics;
    }
    public ArrayList<Card> getGreenApples() {
        return greenApples;
    }
    public ArrayList<Card> getRedApples(){
        return redApples;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Rules getRules() {
        return rules;
    }
    public Phase getCurrentPhase() {
        return currentPhase;
    }
    public int getTotalPlayers() {
        return totalPlayers;
    }
    public int getTotalCurrentPlayers() {
        return players.size();
    }

    public List<Map.Entry<Card,Player>> getSubmittedRedApples() {
        return submittedRedApples;
    }


}

