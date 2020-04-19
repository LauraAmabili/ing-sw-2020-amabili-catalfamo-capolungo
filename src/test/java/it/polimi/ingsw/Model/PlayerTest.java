package it.polimi.ingsw.Model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialBuild_BuildTwiceDifferent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Board board = new Board();
    Worker worker1 = new Worker(1, board);
    Worker worker2 = new Worker(2, board);
    ArrayList<Worker> mockWorkers = new ArrayList<>();
    ArrayList<BoardCell> adjCells = new ArrayList<>();



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAvailableCellsToMove() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);
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
        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);
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
        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);
        mockPlayer.move(0, 2, worker1);
        Assert.assertSame(board.getGrid()[0][2], worker1.getCurCell());
        assertNull(worker1.getOldCell().getWorker());
        assertSame(board.getGrid()[1][2], worker1.getOldCell());
        assertSame(worker1.getCurCell().getWorker(), worker1);
    }


    @Test
    public void testBaseBuild() {
        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);
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
        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        board.getGrid()[1][2].setLevel(3);
        board.getGrid()[0][2].setLevel(1);
        worker1.setCurCell(board.getGrid()[1][2]);
        worker1.setOldCell(board.getGrid()[0][2]);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);
        assertTrue(mockPlayer.checkWin(worker1));
    }


    //SPECIAL MOVES

    @Test
    public void testSpecialBuild_BuildTwiceDifferent() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);
        PlayerInterface player = new SpecialBuild_BuildTwiceDifferent(mockPlayer);
        player.build(1, 0, worker1, 0, 1);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(1, board.getGrid()[0][1].getLevel());
    }

    @Test
    public void testSpecialBuild_BuildTwiceSame() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);

        mockPlayer.build(1, 0, worker1, true);
        assertEquals(2, board.getGrid()[1][0].getLevel());
    }

    @Test
    public void testSpecialBuild_DomeAnyLevel() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);

        mockPlayer.build(1, 0, worker1, true);
        assertEquals(true, board.getGrid()[1][0].getDome());
    }

    @Test
    public void testSpecialMove_MoveTwice() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers);

        mockPlayer.move(2, 0, worker1);
        assertSame(worker1, board.getGrid()[2][0].getWorker());
    }


    @Test
    public void testSpecialMove_PushOpponent() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        worker2.setCurCell(worker1.getBoard().getGrid()[1][0]);
        worker1.setOldCell(null);

        mockPlayer.move(1, 0, worker1);
        assertSame(worker1, board.getGrid()[1][0].getWorker());
        assertSame(worker2, board.getGrid()[2][0].getWorker());

    }

    @Test
    public void testSpecialMove_SwapWorkers() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        worker2.setCurCell(worker1.getBoard().getGrid()[1][0]);
        worker1.setOldCell(null);

        mockPlayer.move(1, 0, worker1);
        assertSame(worker1, board.getGrid()[1][0].getWorker());
        assertSame(worker2, board.getGrid()[0][0].getWorker());

    }

	@Test
	public void testSpecialWin_MoveDown() {

        mockWorkers.add(worker1);
        mockWorkers.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers);
		worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
		worker1.setOldCell(worker1.getBoard().getGrid()[0][1]);
		worker1.setPlayerWorker(mockPlayer);

		worker1.getBoard().getGrid()[0][0].setLevel(1);
		worker1.getBoard().getGrid()[0][1].setLevel(3);

		assertTrue(mockPlayer.checkWin(worker1));
	}



}