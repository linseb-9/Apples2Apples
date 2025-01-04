package com.linseb9.game.core;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.cards.CardBuilder;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.events.GameEventListener;
import com.linseb9.game.phases.Phase;
import com.linseb9.game.phases.SetupPhase;
import com.linseb9.game.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numOfPlayers;
    private int numOfBots;
    private int totalPlayers;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private final ArrayList<Player> players;
    private Phase currentPhase;
    private int gameCounter;
    private CardBuilder cardBuilder;
    private ArrayList<Card> greenApples;
    private ArrayList<Card> redApples;
    private Rules rules;
    private Card currentGreenApple;
    private GameMechanics gameMechanics;



    public Game(int numOfPlayers, int numOfBots){
        this.numOfPlayers = numOfPlayers;
        this.numOfBots = numOfBots;
        this.totalPlayers = numOfPlayers + numOfBots;
        this.currentPhase = new SetupPhase();
        this.gameCounter = 0;
        this.players = new ArrayList<Player>();
        this.gameMechanics = new GameMechanics();
        initializeGame();
    }

    private void initializeGame() {
        rules = new StandardRules(totalPlayers);
        cardBuilder = new CardBuilder();
        greenApples = cardBuilder.buildGreenApples();
        redApples = cardBuilder.buildRedApples();
        gameMechanics.shuffleDeck(greenApples);
        gameMechanics.shuffleDeck(redApples);
    }


    public void performAction(Player player, GameAction action) {
        // Process the action in the current phase
        processActionInPhase(player, action);

        // Check if the phase is complete and transition if necessary
        while (currentPhase.isComplete()) {
            transitionToNextPhase();

            // Handle autonomous phases
            if (currentPhase.isAutonomous()) {
                processAutonomousPhase();
            }
        }
    }

    private void processActionInPhase(Player player, GameAction action) {
        GameEvent event = currentPhase.handle(this, action, player);
        notifyListeners(event);
    }

    private void transitionToNextPhase() {
        currentPhase = currentPhase.nextPhase();
        GameEvent event = new GameEvent(this, null,  currentPhase.getName());
        notifyListeners(event);
    }

    private void processAutonomousPhase() {
        GameEvent event = currentPhase.handle(this, null, null);
        notifyListeners(event);
    }

    private void notifyListeners(GameEvent event) {
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
    public int getTotalPlayers() {
        return totalPlayers;
    }



}

