package com.linseb9.game.core;

import com.linseb9.game.cards.Card;
import com.linseb9.game.players.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The GameMechanics class contain the most basic methods for the game and it the
 * would be used even if custom rules were implemented. Ex shuffleDeck and dealCards
 * which always will be needed.
 */
public class GameMechanics {

    public GameMechanics( ) {
    }

    public void shuffleDeck(ArrayList<Card> deck){
        if (deck.isEmpty()) {
            System.out.println("Deck is empty");
            return;
        }
        Collections.shuffle(deck);
    }

    public Card drawACard(ArrayList<Card> deck) {
        if (deck.isEmpty()) {
            System.out.println("No more cards avaliable");
            return null;
        }
        return deck.remove(0);
    }

    public void dealCards(ArrayList<Player> players, ArrayList<Card> deck) {
        try {
            int numOfPlayerCards = 7;
            while(numOfPlayerCards >= 0) {
                for(Player player: players) {
                    if (deck.isEmpty()) {
                        System.out.println("No more red apples available");
                        return;
                    }
                    if (player.getDeck().size() < 7) {
                        player.getDeck().add(deck.remove(0));
                    }

                }
                numOfPlayerCards--;
            }
        } catch (IndexOutOfBoundsException ioe) {
            System.out.println("To few cards left in deck: " + ioe);
        }


    }
}
