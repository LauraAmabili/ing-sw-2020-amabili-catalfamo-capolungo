package it.polimi.ingsw.Model;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

	Board board = new Board();
	Worker worker1 = new Worker(1, board);
	Worker worker2 = new Worker(2, board);
 	Player mockPlayer;
	ArrayList<Worker> mockWorkers = new ArrayList<>();
	ArrayList<BoardCell> adjCells = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAvailableCellsToMove() {
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
	public void testMove() {
		worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
		worker1.setOldCell(null);
		worker1.setPlayerWorker(mockPlayer);
		mockWorkers.add(worker1);
		mockPlayer = new Player("mockName", mockWorkers);
		mockPlayer.move(0,2, worker1);
		Assert.assertSame(board.getGrid()[0][2], worker1.getCurCell());
		assertNull(worker1.getOldCell().getWorker());
		assertSame(board.getGrid()[1][2], worker1.getOldCell());
		assertSame(worker1.getCurCell().getWorker(), worker1);
	}


	@Test
	public void testBuild() {
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
	public void testCheckWin() {
		board.getGrid()[1][2].setLevel(3);
		board.getGrid()[0][2].setLevel(1);
		worker1.setCurCell(board.getGrid()[1][2]);
		worker1.setOldCell(board.getGrid()[0][2]);
		worker1.setPlayerWorker(mockPlayer);
		mockWorkers.add(worker1);
		mockPlayer = new Player("mockName", mockWorkers);
		assertTrue(mockPlayer.checkWin(worker1));
	}


}