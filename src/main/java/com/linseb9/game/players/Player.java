package com.linseb9.game.players;

import com.linseb9.game.cards.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Serializable {
    private int id;
    private List<Card> deck;
    private boolean judge;
    private int score;

    public Player(int id) {
        this.id = id;
        this.judge = false;
        this.score = 0;
        this.deck = new ArrayList<Card>();
    }

    public int getId(){
        return id;
    }

    public List<Card> getDeck(){
        return deck;
    }

    public int getScore() {
        return score;
    }

    public boolean isJudge() {
        return judge;
    }

    public void incrementScore() {
        this.score += 1;
    }

    public void resetScore(){
        this.score = 0;
    }

    public void setToJudge() {
        this.judge = true;
    }

    public void removeAsJudge() {
        this.judge = false;
    }
}
