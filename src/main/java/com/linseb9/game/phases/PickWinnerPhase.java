package com.linseb9.game.phases;

import com.linseb9.game.actions.GameAction;
import com.linseb9.game.cards.Card;
import com.linseb9.game.core.Game;
import com.linseb9.game.events.GameEvent;
import com.linseb9.game.players.Player;
import com.linseb9.game.util.TerminalFormatting;

public class PickWinnerPhase implements Phase{
    private String gameMessage = "Judge picks a winner";
    private Boolean phaseComplete = false;

    public PickWinnerPhase(){
        this.phaseComplete = false;
    }

    @Override
    public String getMessage() {
        return gameMessage;
    }

    @Override
    public Boolean isAutonomous() {
        return false;
    }

    @Override
    public void handle(Game game, GameAction action, Player player) {
        TerminalFormatting tformat = new TerminalFormatting();
        if (player.judge) {
            int cardNr = action.getCardNr();

            if(cardNr >= 0 && cardNr < game.getTotalCurrentPlayers()- 1) {
                Card winnerCard = game.getSubmittedRedApples().get(cardNr).getKey();
                Player winnerPlayer = game.getSubmittedRedApples().get(cardNr).getValue();
                winnerPlayer.score++;
                phaseComplete = true;
                game.enqueueEvent(new GameEvent(game, action, "Winner is player " + winnerPlayer.id, null));
                game.enqueueEvent(new GameEvent(game, action, tformat.getBlue() +"Congratulations, you won the game!" +tformat.getReset(), winnerPlayer));
                return;
            }

            game.enqueueEvent(new GameEvent(game, action, tformat.getBlue() + "You entered an invalid number, try again" + tformat.getReset() , player));
            return;
        }

        game.enqueueEvent(new GameEvent(game, action, tformat.getBlue()+"You are not the judge" + tformat.getReset() , player));
    }

    @Override
    public Phase nextPhase() {
        return new NextRoundPhase();
    }

    @Override
    public boolean isComplete() {
        return phaseComplete;
    }
}
