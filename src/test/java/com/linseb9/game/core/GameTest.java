package com.linseb9.game.core;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.phases.Phase;
import com.linseb9.game.players.HumanPlayer;
import com.linseb9.game.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;

    @BeforeEach
    void setup() {
        game = new Game(1,2);
    }

    @Test
    void TestStartingAGame() {
        assertNotNull(game, "TEST: Game instance was not created successfully");
    }

    @Test
    void TestAddHumanPlayer() {
        Player player = new HumanPlayer(1);
        game.addPlayers(player);
        ArrayList<Player> players = game.getPlayers();
        assertEquals(1, game.getPlayers().size(), "There should be 1 player  added to the game");
        assertTrue(game.getPlayers().contains(player), "The added player should be in the game.");
    }

    @Test
    void TestRemovePlayer() {
        Player player = new HumanPlayer(1);
        game.addPlayers(player);
        game.removePlayers(player);
        ArrayList<Player> players = game.getPlayers();
        assertEquals(0, game.getPlayers().size(), "There should be 0 players after removing the player from the game");
        assertFalse(game.getPlayers().contains(player), "The removed player should not be in the game.");
    }

    @Test
    void TestGameInitialization() {
        Game game = new Game(3, 2);
        assertEquals(5, game.getTotalPlayers(), "Total players should match the sum of players and bots.");
        assertNotNull(game.getGreenApples(), "Green apples deck should be initialized.");
        assertNotNull(game.getRedApples(), "Red apples deck should be initialized.");
        assertNotNull(game.getRules(), "Rules should be initialized.");
        assertFalse(game.getGreenApples().isEmpty() , "Green apples deck should not be empty.");
        assertFalse(game.getRedApples().isEmpty(), "Red apples deck should not be empty.");
    }

    @Test
    void testPhaseTransition() {
        Game game = new Game(2, 0);
        Phase initialPhase = game.getCurrentPhase();
        Player player1 = new HumanPlayer(1);
        Player player2 = new HumanPlayer(2);
        game.addPlayers(player1);
        game.addPlayers(player2);
        // Simulate phase completion
        GameAction action = new GameAction("JOINED GAME", 0);
        game.performAction(player1, action);
        game.performAction(player2, action);

        Phase newPhase = game.getCurrentPhase();
        assertNotEquals(initialPhase, newPhase, "Phase should transition to the next phase.");
    }

}
