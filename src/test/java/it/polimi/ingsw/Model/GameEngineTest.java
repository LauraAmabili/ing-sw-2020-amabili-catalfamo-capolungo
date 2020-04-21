package it.polimi.ingsw.Model;

import it.polimi.ingsw.Controller.GameEngine;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameEngineTest {

    GameEngine gameEngine = new GameEngine();

    @Before
    public void setUp() throws Exception {
        gameEngine.addNickname("SuperRexo");
        gameEngine.addNickname("NotATeen");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void delPlayer() {
        gameEngine.initialiseMatch();
        BoardCell b1 = new BoardCell(0, 0);
        BoardCell b2 = new BoardCell(0, 1);
        PlayerInterface player1 = gameEngine.getOnlinePlayers().get(0);
        PlayerInterface player2 = gameEngine.getOnlinePlayers().get(1);
        b1.setWorker(player1.getWorkerRef()[0]);
        b2.setWorker(player1.getWorkerRef()[1]);
        player1.getWorkerRef()[0].setCurCell(b1);
        player1.getWorkerRef()[1].setCurCell(b2);
        gameEngine.delPlayer(gameEngine.getOnlinePlayers().get(0));
        Assert.assertEquals(gameEngine.getOnlinePlayers().get(0), player2);
        Assert.assertNull(player1.getWorkerRef()[0]);
        Assert.assertNull(player1.getWorkerRef()[1]);
        Assert.assertNull(b1.getWorker());
        Assert.assertNull(b2.getWorker());
    }

    @Test
    public void initialiseMatch() {
        gameEngine.initialiseMatch();
        Assert.assertNotNull(gameEngine.getOnlinePlayers().get(0));
        Assert.assertNotNull(gameEngine.getOnlinePlayers().get(1));
        Assert.assertNotNull(gameEngine.getOnlinePlayers().get(0).getWorkerRef()[0]);
        Assert.assertNotNull(gameEngine.getOnlinePlayers().get(0).getWorkerRef()[1]);
        Assert.assertNotNull(gameEngine.getOnlinePlayers().get(1).getWorkerRef()[0]);
        Assert.assertNotNull(gameEngine.getOnlinePlayers().get(1).getWorkerRef()[1]);
    }


}