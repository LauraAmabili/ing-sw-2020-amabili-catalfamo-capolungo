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
        for (int j = 0; j < game.getOnlinePlayers().size(); j++) {
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
        game.getChosenGods().add(new God("Apollo"));
        game.getChosenGods().add(new God("Artemis"));
        turn2.getCurrentPlayer().getPlayerState().chosenCards("Athena");
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
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        turn2.getCurrentPlayer().getPlayerState().setCard("Apollo");
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
        turn2.getCurrentPlayer().getPlayerState().setCard("Athena");

        //NextTurn() when added worker on board
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        assertEquals(new PlaceWorker(turn2.getCurrentPlayer(), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().addWorker(0, 0, turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.getCurrentPlayer().getPlayerState().placeWorker(2,2, 1);
        assertEquals(new Idle(turn2.getActivePlayers().get(i), new PlaceWorker(turn2.getActivePlayers().get(i), game), game), turn2.getActivePlayers().get(i).getPlayerState());
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
        turn2.getCurrentPlayer().addWorker(2,2, turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.getCurrentPlayer().getPlayerState().placeWorker(2,3, 1);
        turn2.getCurrentPlayer().addWorker(3,3, turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.getCurrentPlayer().getPlayerState().placeWorker(3,4, 1);

        //NextTurn() when moved worker and built building
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        assertEquals(new Moving(turn2.getCurrentPlayer(), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().getPlayerState().move(1,2,1);
        assertEquals(new Building(turn2.getCurrentPlayer(), game), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().getPlayerState().build(1,3,1);
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