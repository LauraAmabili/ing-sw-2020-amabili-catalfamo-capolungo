package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.PlayerFSA.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TurnTest {

    Game game = new Game();
    Board board = new Board();
    List<Worker> list = new ArrayList<>();

    @Before
    public void setUp() {
        game.getOnlinePlayers().clear();
    }

    @After
    public void tearDown() {

    }


    @Test
    public void TurnTest() {

        //Create Challenger
        int i = 0;
        Turn turn1 = new Turn(game.getOnlinePlayers());
        game.setCurrentTurn(turn1);
        game.initialiseMatch(3);
        PlayerInterface player = null;
        game.createChallenger();
        for (PlayerInterface p : turn1.getActivePlayers()) {
            if (turn1.getCurrentPlayer() == p) {
                player = p;
                i = turn1.getActivePlayers().indexOf(p);
            }
        }
        for (int j = 0; j < game.getOnlinePlayers().size() - 1; j++) {
            if (i != j) {
                Assert.assertEquals(turn1.getActivePlayers().get(j).getPlayerState(), new AddNickname(turn1.getActivePlayers().get(j), game));
            }
        }
        assert player != null;
        Assert.assertEquals(player.getPlayerState(), new Initialized(player, game));


        //NextTurn() when added nickname
        setUp();
        i = 0;
        Turn turn2 = new Turn(game.getOnlinePlayers());
        game.setCurrentTurn(turn2);
        game.initialiseMatch(3);
        turn2.getActivePlayers().get(0).getPlayerState().addNickname("Rexo");
        turn2.getActivePlayers().get(1).getPlayerState().addNickname("NotATeen");
        turn2.getActivePlayers().get(2).getPlayerState().addNickname("Walter");
        game.createChallenger();
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        List<God> godList = new ArrayList<>();
        God Apollo = new God("Apollo");
        God Artemis = new God("Artemis");
        God Athena = new God("Athena");


        godList.add(Apollo);
        godList.add(Artemis);
        godList.add(Athena);
        turn2.getCurrentPlayer().getPlayerState().chosenCards("Apollo");
        turn2.getCurrentPlayer().getPlayerState().chosenCards("Artemis");
        turn2.getCurrentPlayer().getPlayerState().chosenCards("Athena");
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new SetCard(turn2.getActivePlayers().get(0), game), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new AddNickname(turn2.getActivePlayers().get(2), game), game), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new SetCard(turn2.getActivePlayers().get(i + 1), game), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new AddNickname(turn2.getActivePlayers().get(i), game),game), turn2.getActivePlayers().get(i).getPlayerState());
        }

        //NextTurn() when set card
        turn2.getCurrentPlayer().getPlayerState().setCard("Apollo");
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new SetCard(turn2.getActivePlayers().get(0), game), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new SetCard(turn2.getActivePlayers().get(2), game), game), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new SetCard(turn2.getActivePlayers().get(i + 1), game), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new SetCard(turn2.getActivePlayers().get(i), game), game), turn2.getActivePlayers().get(i).getPlayerState());
        }

        //One full turn passes
        turn2.getCurrentPlayer().getPlayerState().setCard("Artemis");
        turn2.nextTurn();
        turn2.getCurrentPlayer().getPlayerState().setCard("Athena");
        turn2.nextTurn();

        //NextTurn() when added worker on board
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        assertEquals(new PlaceWorker(turn2.getCurrentPlayer(), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().getPlayerState().placeWorker(1, 1, 1);
        assertEquals(new Idle(turn2.getCurrentPlayer(), new PlaceWorker(turn2.getCurrentPlayer(), game), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().getPlayerState().placeWorker(1,2, 2);
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new PlaceWorker(turn2.getActivePlayers().get(0), game), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new PlaceWorker(turn2.getActivePlayers().get(2), game), game), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new PlaceWorker(turn2.getActivePlayers().get(i + 1), game), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new PlaceWorker(turn2.getActivePlayers().get(i), game), game), turn2.getActivePlayers().get(i).getPlayerState());
        }

        //One full turn passes
        turn2.getCurrentPlayer().getPlayerState().placeWorker(2,2, 1);
        turn2.getCurrentPlayer().getPlayerState().placeWorker(2,3, 2);
        turn2.nextTurn();
        turn2.getCurrentPlayer().getPlayerState().placeWorker(3,3, 1);
        turn2.getCurrentPlayer().getPlayerState().placeWorker(3,4, 2);
        turn2.nextTurn();

        //NextTurn() when moved worker and built building
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        assertEquals(new Moving(turn2.getCurrentPlayer(), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().getPlayerState().move(0,0,1);
        assertEquals(new Building(turn2.getCurrentPlayer(), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().getPlayerState().build(0,1,1);
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new Moving(turn2.getActivePlayers().get(0), game), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new Building(turn2.getActivePlayers().get(2), game), game), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new Moving(turn2.getActivePlayers().get(i + 1), game), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new Building(turn2.getActivePlayers().get(i), game), game), turn2.getActivePlayers().get(i).getPlayerState());
        }
    }

}