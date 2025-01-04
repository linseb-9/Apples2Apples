package com.linseb9.game.cards;

public class RedApple implements Card {
    private String title;
    private String description;

    public RedApple(String title, String description){
        this.title = title;
        this.description = description;

    }

    @Override
    public String attributes() {
        return "Title: " + title + " Description: " + description;
    }
}
