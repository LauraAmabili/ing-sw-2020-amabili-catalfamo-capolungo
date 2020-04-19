package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    Board board = new Board();
    Worker worker1 = new Worker(1, board);
    Worker worker2 = new Worker(2, board);
    ArrayList<Worker> mockWorkers1 = new ArrayList<>();
    ArrayList<Worker> mockWorkers2 = new ArrayList<>();
    ArrayList<BoardCell> adjCells = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAvailableCellsToMove() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        board.getGrid()[0][1].setWorker(worker2);
        board.getGrid()[2][3].setLevel(3);
        adjCells.add(board.getGrid()[0][2]);
        adjCells.add(board.getGrid()[0][3]);
        adjCells.add(board.getGrid()[1][1]);
        adjCells.add(board.getGrid()[1][3]);
        adjCells.add(board.getGrid()[2][1]);
        adjCells.add(board.getGrid()[2][2]);
        Assert.assertEquals(adjCells, mockPlayer.availableCellsToMove(worker1));
    }

    @Test
    public void testAvailableCellsToBuild() {
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        board.getGrid()[0][1].setWorker(worker2);
        board.getGrid()[1][1].setLevel(3);
        board.getGrid()[1][1].setDome(true);
        board.getGrid()[2][3].setLevel(3);
        adjCells.add(board.getGrid()[0][2]);
        adjCells.add(board.getGrid()[0][3]);
        adjCells.add(board.getGrid()[1][3]);
        adjCells.add(board.getGrid()[2][1]);
        adjCells.add(board.getGrid()[2][2]);
        adjCells.add(board.getGrid()[2][3]);
        Assert.assertEquals(adjCells, mockPlayer.availableCellsToBuild(worker1));
    }


    @Test
    public void testBaseMove() {
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        mockPlayer.move(0, 2, worker1);
        Assert.assertSame(board.getGrid()[0][2], worker1.getCurCell());
        assertNull(worker1.getOldCell().getWorker());
        assertSame(board.getGrid()[1][2], worker1.getOldCell());
        assertSame(worker1.getCurCell().getWorker(), worker1);
    }


    @Test
    public void testBaseBuild() {
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        board.getGrid()[0][1].setLevel(2);
        board.getGrid()[0][2].setLevel(3);
        mockPlayer.build(0, 1, worker1);
        mockPlayer.build(0, 2, worker1);
        assertEquals(3, board.getGrid()[0][1].getLevel());
        assertTrue(board.getGrid()[0][2].getDome());
        assertEquals(3, board.getGrid()[0][2].getLevel());
    }


    @Test
    public void testBaseCheckWin() {
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        board.getGrid()[1][2].setLevel(3);
        board.getGrid()[0][2].setLevel(1);
        worker1.setCurCell(board.getGrid()[1][2]);
        worker1.setOldCell(board.getGrid()[0][2]);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        assertTrue(mockPlayer.checkWin(worker1));
    }


    //SPECIAL MOVES

    @Test
    public void testSpecialBuild_BuildTwiceDifferent() //WORKING
    {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        PlayerInterface player = new SpecialBuild_BuildTwiceDifferent(mockPlayer);


        player.build(1, 0, worker1, 0, 1);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(1, board.getGrid()[0][1].getLevel());
    }

    @Test
    public void testSpecialBuild_BuildTwiceSame() { //WORKING

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        PlayerInterface player = new SpecialBuild_BuildTwiceSame(mockPlayer);

        player.build(1, 0, worker1, true);
        assertEquals(2, board.getGrid()[1][0].getLevel());
    }

    @Test
    public void testSpecialBuild_DomeAnyLevel() { //WORKING

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        PlayerInterface player = new SpecialBuild_DomeAnyLevel(mockPlayer);


        player.build(1, 0, worker1, true);
        assertEquals(true, board.getGrid()[1][0].getDome());
    }

    @Test
    public void testSpecialMove_MoveTwice() { //WORKING

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1);
        PlayerInterface player = new SpecialMove_MoveTwice(mockPlayer);

        assertFalse(player.move(0, 3, worker1));
        assertSame(null, board.getGrid()[0][3].getWorker());
        assertTrue(player.move(2, 2, worker1));
        assertSame(worker1, board.getGrid()[2][2].getWorker());
    }


    @Test
    public void testSpecialMove_PushOpponent() {

        mockWorkers1.add(worker1);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);

        mockWorkers2.add(worker2);
        worker1.setCurCell(worker1.getBoard().getGrid()[3][0]);

        Player mockPlayer1 = new Player("mockName1", mockWorkers1);
        worker1.setPlayerWorker(mockPlayer1);
        Player mockPlayer2 = new Player("mockName2", mockWorkers2);
        worker2.setPlayerWorker(mockPlayer2);

        PlayerInterface player = new SpecialMove_PushOpponent(mockPlayer1);


        assertTrue(player.move(1, 0, worker1));
        /*assertSame(worker1, board.getGrid()[1][0].getWorker());
        assertSame(worker2, board.getGrid()[2][0].getWorker());
        */
    }

}