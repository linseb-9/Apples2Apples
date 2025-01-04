package com.linseb9.game.cards;

public class GreenApple implements Card {
    private String characteristic;
    private String synonym;

    public GreenApple(String characteristic, String synonym) {
        this.characteristic = characteristic;
        this.synonym = synonym;
    }

    @Override
    public String attributes() {
        return "Characteristics: " + characteristic + " Synonym: " + synonym;
    }
}
