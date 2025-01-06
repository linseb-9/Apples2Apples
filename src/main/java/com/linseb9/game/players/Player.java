package com.linseb9.game.players;

import com.linseb9.game.cards.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    public int id;
    public List<Card> deck;
    public boolean judge;
    public int score;


    public Player(int id){
        this.id = id;
        deck = new ArrayList<Card>();
    }

}
