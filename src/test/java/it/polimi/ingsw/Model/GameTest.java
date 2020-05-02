package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    Game game = new Game();

    @Before
    public void setUp() throws Exception {
        game.getCurrentTurn().getCurrentPlayer().addNickname("SuperRexo");
        game.getCurrentTurn().getCurrentPlayer().addNickname("Notateen");


    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void delPlayer() {
        game.initialiseMatch(2);
        BoardCell b1 = new BoardCell(0, 0);
        BoardCell b2 = new BoardCell(0, 1);
        PlayerInterface player1 = game.getOnlinePlayers().get(0);
        PlayerInterface player2 = game.getOnlinePlayers().get(1);
        List<PlayerInterface> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        Turn turn1 = new Turn(playerList);
        game.setCurrentTurn(turn1);
        b1.setWorker(player1.getWorkerRef().get(0));
        b2.setWorker(player1.getWorkerRef().get(1));
        player1.getWorkerRef().get(0).setCurCell(b1);
        player1.getWorkerRef().get(1).setCurCell(b2);
        game.delPlayer(game.getOnlinePlayers().get(0));
        Assert.assertEquals(game.getOnlinePlayers().get(0), player2);
        Assert.assertEquals(0, player1.getWorkerRef().size());
        Assert.assertNull(b1.getWorker());
        Assert.assertNull(b2.getWorker());
    }


    @Test
    public void initialiseMatch() {
        game.initialiseMatch(2);
        Assert.assertNotNull(game.getOnlinePlayers().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(1));
        Assert.assertNotNull(game.getOnlinePlayers().get(0).getWorkerRef().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(0).getWorkerRef().get(1));
        Assert.assertNotNull(game.getOnlinePlayers().get(1).getWorkerRef().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(1).getWorkerRef().get(1));
    }

    /*
    State state = State.ADDNICKNAME;
    assertEquals(game.getCurrentTurn().getCurrentPlayer().getNickname(), "Notateen");
     */

}