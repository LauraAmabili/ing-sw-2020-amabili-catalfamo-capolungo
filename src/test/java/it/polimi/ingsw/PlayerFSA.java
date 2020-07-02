package it.polimi.ingsw;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.PlayerFSA.*;
import it.polimi.ingsw.Model.Turn;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PlayerFSA {

    @Test
    public void MovingTest() {
        Game game = new Game();
        Board board = new Board();
        Turn turn = new Turn(game.getOnlinePlayers());
        game.setBoard(board);
        game.setCurrentTurn(turn);
        game.initialiseMatch(2);
        game.getStateList().get(0).addNickname("Rexo");
        game.getStateList().get(1).addNickname("NotATeen");
        for (int i = 0; i < game.getStateList().size(); i++) {
            game.getStateList().set(i, new Idle(game.getOnlinePlayers().get(i), new AddNickname(game.getOnlinePlayers().get(i), game), game));
        }
        game.getStateList().set(0, new Initialized(game.getOnlinePlayers().get(0), game));
        assertEquals(new Initialized(game.getOnlinePlayers().get(0), game), game.getStateList().get(0));
        game.getStateList().get(0).chosenCard("Artemis");
        game.getStateList().get(0).chosenCard("Demeter");
        game.getStateList().set(0, new SetCard(game.getOnlinePlayers().get(0), game));
        assertEquals(new SetCard(turn.getCurrentPlayer(), game), game.getStateList().get(0));
        game.getStateList().get(0).setCard("Artemis");
        game.getStateList().set(0, new SetCard(game.getOnlinePlayers().get(0), game));
        game.getStateList().get(1).setCard("Demeter");
        assertEquals(new PlaceWorker(turn.getCurrentPlayer(), game), game.getStateList().get(0));
        game.getStateList().get(0).placeWorker(1, 1, 0);
        game.getStateList().get(0).placeWorker(1, 2, 1);
        assertEquals(new PlaceWorker(turn.getCurrentPlayer(), game), game.getStateList().get(1));
        game.getStateList().get(1).placeWorker(2, 1, 0);
        game.getStateList().get(1).placeWorker(2, 2, 1);
        game.getStateList().get(0).canIMove();
        game.getStateList().get(0).checkWorker(1, true);
        assertEquals(new Moving(turn.getCurrentPlayer(), game), game.getStateList().get(0));
        game.getStateList().get(0).move(1, 3, 2,3, 2);
        game.getStateList().get(0).checkBuild(1, true);
        assertEquals(new Building(turn.getCurrentPlayer(), game), game.getStateList().get(0));
        game.getStateList().get(0).build(2, 5, 1);
    }

}
