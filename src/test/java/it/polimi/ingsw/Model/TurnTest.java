package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
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
    List<PlayerInterface> playerlist = new ArrayList<>();

    @Before
    public void setUp(){
        playerlist.clear();
        playerlist.add(new Player());
        playerlist.add(new Player());
        playerlist.add(new Player());
    }

    @After
    public void tearDown() {
        int counterId = 0;
        for (PlayerInterface playerInterface : playerlist) {
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
        Turn turn1 = new Turn(playerlist);
        PlayerInterface player = null;
        turn1.createChallenger();
        for (PlayerInterface p : turn1.getActivePlayers()) {
            if (turn1.getCurrentPlayer() == p) {
                player = p;
                i = turn1.getActivePlayers().indexOf(p);
            }
        }
        for (int j = 0; j < playerlist.size(); j++) {
            if (i != j) {
                Assert.assertEquals(turn1.getActivePlayers().get(j).getPlayerState(), turn1.getActivePlayers().get(j).getAddNickname());
            }
        }
        assert player != null;
        Assert.assertEquals(player.getPlayerState(), player.getInitialized());


        //NextTurn() when added nickname
        setUp();
        tearDown();
        i = 0;
        Turn turn2 = new Turn(playerlist);
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
        God Apollo = new God("Apollo", false, false, false, false, false, false, true, false, false);
        God Artemis = new God("Artemis", false, false, false, false, true, false, false, false, false);
        God Athena = new God("Athena", false, false, false, false, false, false, false, true, false);



        godList.add(Apollo);
        godList.add(Artemis);
        godList.add(Athena);
        turn2.getCurrentPlayer().setChosenGods(godList);
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(turn2.getActivePlayers().get(0).getSetCard(), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(turn2.getActivePlayers().get(2).getIdle(), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(turn2.getActivePlayers().get(i + 1).getSetCard(), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(turn2.getActivePlayers().get(i).getIdle(), turn2.getActivePlayers().get(i).getPlayerState());
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
            assertEquals(turn2.getActivePlayers().get(0).getSetCard(), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(turn2.getActivePlayers().get(2).getIdle(), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(turn2.getActivePlayers().get(i + 1).getSetCard(), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(turn2.getActivePlayers().get(i).getIdle(), turn2.getActivePlayers().get(i).getPlayerState());
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
        assertEquals(turn2.getCurrentPlayer().getPlaceWorker(), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().PlaceWorker(1,1, turn2.getCurrentPlayer().getWorkerRef().get(0));
        assertEquals(turn2.getCurrentPlayer().getIdle(), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().PlaceWorker(1,2, turn2.getCurrentPlayer().getWorkerRef().get(1));
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(turn2.getActivePlayers().get(0).getPlaceWorker(), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(turn2.getActivePlayers().get(2).getIdle(), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(turn2.getActivePlayers().get(i + 1).getPlaceWorker(), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(turn2.getActivePlayers().get(i).getIdle(), turn2.getActivePlayers().get(i).getPlayerState());
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
        assertEquals(turn2.getCurrentPlayer().getMoving(), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().StateMove(0,0,turn2.getCurrentPlayer().getWorkerRef().get(0));
        assertEquals(turn2.getCurrentPlayer().getBuilding(), turn2.getCurrentPlayer().getPlayerState());
        turn2.getCurrentPlayer().StateBuild(0,1,turn2.getCurrentPlayer().getWorkerRef().get(0));
        turn2.nextTurn();
        if (i == 2) {
            assertEquals(turn2.getActivePlayers().get(0).getMoving(), turn2.getActivePlayers().get(0).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(0));
            assertEquals(turn2.getActivePlayers().get(2).getIdle(), turn2.getActivePlayers().get(2).getPlayerState());
        } else {
            assertEquals(turn2.getActivePlayers().get(i + 1).getMoving(), turn2.getActivePlayers().get(i + 1).getPlayerState());
            assertEquals(turn2.getCurrentPlayer(), turn2.getActivePlayers().get(i + 1));
            assertEquals(turn2.getActivePlayers().get(i).getIdle(), turn2.getActivePlayers().get(i).getPlayerState());
        }
    }
}