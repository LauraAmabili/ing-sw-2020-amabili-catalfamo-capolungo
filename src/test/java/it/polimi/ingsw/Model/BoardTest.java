package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BoardTest {

    Board board = new Board();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initialiseGrid() {

        Assert.assertNotNull(board.grid);
    }

    @Test
    public void deleteWorkers() {
        ArrayList<Worker> list = new ArrayList<>();
        Worker worker1 = new Worker(1, board);
        worker1.setCurCell(board.getGrid()[0][0]);
        Worker worker2 = new Worker(2, board);
        worker2.setCurCell(board.getGrid()[0][1]);
        list.add(worker1);
        list.add(worker2);
        Player player = new Player("SuperRexo", list);
        board.deleteWorkers(player);
        for (int i = 0; i < player.getWorkerRef().length; i++) {
            Assert.assertNull(player.getWorkerRef()[i]);
        }
    }

    @Test
    public void adjacentCells() {
        ArrayList<BoardCell> b = new ArrayList<>();
        Worker worker = new Worker(1, board);
        worker.setCurCell(board.getGrid()[3][3]);
        b.add(board.getGrid()[2][2]);
        b.add(board.getGrid()[2][3]);
        b.add(board.getGrid()[2][4]);
        b.add(board.getGrid()[3][2]);
        b.add(board.getGrid()[3][4]);
        b.add(board.getGrid()[4][2]);
        b.add(board.getGrid()[4][3]);
        b.add(board.getGrid()[4][4]);
        Assert.assertEquals(board.adjacentCells(worker.getCurCell()), b);
    }

    @Test
    public void freeCells() {
        Worker worker1 = new Worker(1, board);
        Worker worker2 = new Worker(2, board);
        ArrayList<BoardCell> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                list.add(board.getGrid()[i][j]);
            }
        }
        list.remove(board.getGrid()[3][3]);
        list.remove(board.getGrid()[0][0]);
        board.getGrid()[3][3].setWorker(worker1);
        board.getGrid()[0][0].setWorker(worker2);
        Assert.assertEquals(board.freeCells(), list);
    }
}