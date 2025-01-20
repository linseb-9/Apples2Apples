package com.linseb9.game.players;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.actions.GameActionDispatcher;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.EventDispatcher;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.phases.PickWinnerPhase;
import com.linseb9.game.phases.SubmitRedApplePhase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotPlayer extends Player implements Runnable, Serializable {
    private Game game;
    private GameActionDispatcher gameActionDispatcher;
    private EventDispatcher eventDispatcher;
    private Random random;

    public BotPlayer(int id, Game game, EventDispatcher eventDispatcher, GameActionDispatcher gameActionDispatcher){
        super(id);
        this.game = game;
        this.gameActionDispatcher = gameActionDispatcher;
        this.eventDispatcher = eventDispatcher;
        this.random = new Random();
    }


    @Override
    public void run() {
        game.addPlayers(this);
        eventDispatcher.addClient(this);
        GameAction action = new GameAction("JOINED GAME", 0);
        gameActionDispatcher.dispatchAction(action, this);
    }

    public void eventReader(GameEvent event) {
        try{
            if (event instanceof GameEvent gameEvent) {
                if (gameEvent.getSource().getClass() == SubmitRedApplePhase.class && !this.isJudge()) {
                    playRedApple();
                    return;
                }
                if (gameEvent.getSource().getClass() == PickWinnerPhase.class && this.isJudge()) {
                    chooseWinner();
                }

            }
        }
        catch(Exception e) {
            System.out.println("Error while forwarding message: " + e);
        }
    }

    private void chooseWinner() {
        int cardNr = random.nextInt(0,game.getTotalCurrentPlayers() -1);
        GameAction action = new GameAction("PLAY CARD", cardNr);
        gameActionDispatcher.dispatchAction(action, this);
    }

    private void playRedApple() {
        int cardNr = random.nextInt(0, 7);
        GameAction action = new GameAction("PLAY CARD", cardNr);
        gameActionDispatcher.dispatchAction(action, this);
    }
}
