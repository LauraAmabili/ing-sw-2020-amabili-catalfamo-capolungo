package it.polimi.ingsw.Model;

import it.polimi.ingsw.Decorator.PlayerInterface;
import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.Worker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    Game game = new Game();

    @Before
    public void setUp() throws Exception {
        game.addNickname("SuperRexo");
        game.addNickname("NotATeen");

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
        b1.setWorker(player1.getWorkerRef()[0]);
        b2.setWorker(player1.getWorkerRef()[1]);
        player1.getWorkerRef()[0].setCurCell(b1);
        player1.getWorkerRef()[1].setCurCell(b2);
        game.delPlayer(game.getOnlinePlayers().get(0));
        Assert.assertEquals(game.getOnlinePlayers().get(0), player2);
        Assert.assertNull(player1.getWorkerRef()[0]);
        Assert.assertNull(player1.getWorkerRef()[1]);
        Assert.assertNull(b1.getWorker());
        Assert.assertNull(b2.getWorker());
    }

    @Test
    public void initialiseMatch() {
        game.initialiseMatch();
        Assert.assertNotNull(game.getOnlinePlayers().get(0));
        Assert.assertNotNull(game.getOnlinePlayers().get(1));
        Assert.assertNotNull(game.getOnlinePlayers().get(0).getWorkerRef()[0]);
        Assert.assertNotNull(game.getOnlinePlayers().get(0).getWorkerRef()[1]);
        Assert.assertNotNull(game.getOnlinePlayers().get(1).getWorkerRef()[0]);
        Assert.assertNotNull(game.getOnlinePlayers().get(1).getWorkerRef()[1]);
    }

}