package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        game.initialiseMatch();
        BoardCell b1 = new BoardCell(0, 0);
        BoardCell b2 = new BoardCell(0, 1);
        PlayerInterface player1 = game.getOnlinePlayers().get(0);
        PlayerInterface player2 = game.getOnlinePlayers().get(1);
        b1.setWorker(player1.getWorkerRef().get(0));
        b2.setWorker(player1.getWorkerRef().get(1));
        player1.getWorkerRef().get(0).setCurCell(b1);
        player1.getWorkerRef().get(1).setCurCell(b2);
        game.delPlayer(game.getOnlinePlayers().get(0));
        Assert.assertEquals(game.getOnlinePlayers().get(0), player2);
        Assert.assertNull(player1.getWorkerRef().get(0));
        Assert.assertNull(player1.getWorkerRef().get(1));
        Assert.assertNull(b1.getWorker());
        Assert.assertNull(b2.getWorker());
    }


    @Test
    public void initialiseMatch() {
        game.initialiseMatch();
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