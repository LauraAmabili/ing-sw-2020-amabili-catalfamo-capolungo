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

    Board board = new Board();
    List<Worker> list = new ArrayList<>();
    List<PlayerInterface> playerList = new ArrayList<>();

    @Before
    public void setUp() {
        playerList.clear();
        playerList.add(new Player());
        playerList.add(new Player());
        playerList.add(new Player());
    }

    @After
    public void tearDown() {
        int counterId = 0;
        for (PlayerInterface playerInterface : playerList) {
            for (int i = 0; i < 2; i++, counterId++) {
                Worker worker = new Worker(counterId);
                list.add(worker);
            }
            playerInterface.setWorkerRef(list);
            playerInterface.setBoard(board);
            list.clear();
        }
    }


    @Test
    public void TurnTest() {

        //Create Challenger
        tearDown();
        int i = 0;
        Turn turn1 = new Turn(playerList);
        PlayerInterface player = null;
        turn1.createChallenger();
        for (PlayerInterface p : turn1.getActivePlayers()) {
            if (turn1.getCurrentPlayer() == p) {
                player = p;
                i = turn1.getActivePlayers().indexOf(p);
            }
        }
        for (int j = 0; j < playerList.size() - 1; j++) {
            if (i != j) {
                Assert.assertEquals(turn1.getActivePlayers().get(j).getPlayerState(), new AddNickname(turn1.getActivePlayers().get(j)));
            }
        }
        assert player != null;
        Assert.assertEquals(player.getPlayerState(), new Initialized(player));


        //NextTurn() when added nickname
        setUp();
        tearDown();
        i = 0;
        Turn turn2 = new Turn(playerList);
        turn2.createChallenger();
        turn2.getActivePlayers().get(0).addNickname("Rexo");
        turn2.getActivePlayers().get(1).addNickname("NotATeen");
        turn2.getActivePlayers().get(2).addNickname("Walter");
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
        turn2.getCurrentPlayer().setChosenGods(godList);
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new SetCard(turn2.getActivePlayers().get(0)), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new AddNickname(turn2.getActivePlayers().get(2))), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new SetCard(turn2.getActivePlayers().get(i + 1)), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new AddNickname(turn2.getActivePlayers().get(i))), turn2.getActivePlayers().get(i).getPlayerState());
        }

        //NextTurn() when set card
        turn2.getCurrentPlayer().setCard(Apollo);
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new SetCard(turn2.getActivePlayers().get(0)), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new SetCard(turn2.getActivePlayers().get(2))), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new SetCard(turn2.getActivePlayers().get(i + 1)), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new SetCard(turn2.getActivePlayers().get(i))), turn2.getActivePlayers().get(i).getPlayerState());
        }

        //One full turn passes
        turn2.getCurrentPlayer().setCard(Artemis);
        turn2.nextTurn();
        turn2.getCurrentPlayer().setCard(Athena);
        turn2.nextTurn();

        //NextTurn() when added worker on board
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        assertEquals(new PlaceWorker(turn2.getCurrentPlayer()), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().PlaceWorker(1,1, turn2.getCurrentPlayer().getWorkerRef().get(0));
        assertEquals(new Idle(turn2.getCurrentPlayer(), new PlaceWorker(turn2.getCurrentPlayer())), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().PlaceWorker(1,2, turn2.getCurrentPlayer().getWorkerRef().get(1));
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new PlaceWorker(turn2.getActivePlayers().get(0)), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new PlaceWorker(turn2.getActivePlayers().get(2))), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new PlaceWorker(turn2.getActivePlayers().get(i + 1)), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new PlaceWorker(turn2.getActivePlayers().get(i))), turn2.getActivePlayers().get(i).getPlayerState());
        }

        //One full turn passes
        turn2.getCurrentPlayer().PlaceWorker(2,2, turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.getCurrentPlayer().PlaceWorker(2,3, turn2.getCurrentPlayer().getWorkerRef().get(1));
        turn2.nextTurn();
        turn2.getCurrentPlayer().PlaceWorker(3,3, turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.getCurrentPlayer().PlaceWorker(3,4, turn2.getCurrentPlayer().getWorkerRef().get(1));
        turn2.nextTurn();

        //NextTurn() when moved worker and built building
        for (PlayerInterface p : turn2.getActivePlayers()) {
            if (turn2.getCurrentPlayer() == p) {
                i = turn2.getActivePlayers().indexOf(p);
            }
        }
        assertEquals(new Moving(turn2.getCurrentPlayer()), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().StateMove(0,0,turn2.getCurrentPlayer().getWorkerRef().get(0));
        assertEquals(new Building(turn2.getCurrentPlayer()), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().StateBuild(0,1,turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(new Moving(turn2.getActivePlayers().get(0)), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(new Idle(turn2.getActivePlayers().get(2), new Building(turn2.getActivePlayers().get(2))), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(new Moving(turn2.getActivePlayers().get(i + 1)), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(new Idle(turn2.getActivePlayers().get(i), new Building(turn2.getActivePlayers().get(i))), turn2.getActivePlayers().get(i).getPlayerState());
        }
    }

}