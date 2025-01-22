package com.linseb9.game.players;



import java.io.Serializable;

/**
 * The HumanPlayer is created inside the ClientHandler when a client joins the server.
 * It uses only the Player class's methods.
 */
public class HumanPlayer extends Player implements Serializable {

    public HumanPlayer(int id){
        super(id);

    }

}
