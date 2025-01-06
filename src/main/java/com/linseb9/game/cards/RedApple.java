package com.linseb9.game.cards;

import java.io.Serializable;

public class RedApple implements Card, Serializable  {
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
