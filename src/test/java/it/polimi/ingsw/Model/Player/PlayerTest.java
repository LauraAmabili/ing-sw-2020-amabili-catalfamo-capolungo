package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    Game game = new Game();
    ArrayList<BoardCell> adjCells = new ArrayList<>();
    private List<Worker> mockWorkers1 = new ArrayList<Worker>();
    private List<Worker> mockWorkers2 = new ArrayList<Worker>();
    Worker worker2 = new Worker(2);
    Worker worker1 = new Worker(1);
    Board  board = new Board();


    @Before
    public void setUp() {

    }

 @Test
    public void testAvailableCellsToMove() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(mockPlayer.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
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
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(mockPlayer.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
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
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(mockPlayer.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
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
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(mockPlayer.getBoard().getGrid()[1][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
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
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        board.getGrid()[1][2].setLevel(3);
        board.getGrid()[0][2].setLevel(1);
        worker1.setCurCell(board.getGrid()[1][2]);
        worker1.setOldCell(board.getGrid()[0][2]);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        assertTrue(mockPlayer.checkWin(worker1));

    }

    @Test
    public void addWorkerTest() {
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        board.getGrid()[0][0].setWorker(worker1);
        List<BoardCell> free = new ArrayList<>();
        for(BoardCell[] b : board.getGrid()) {
            for (BoardCell c : b) {
                if(c.getWorker() != null) {
                    free.add(c);
                }
            }
        }
        PlayerInterface player = new Player("abc", mockWorkers1, board);
        player.addWorker(0,0, player.getWorkerRef().get(1));
        assertFalse(player.addWorker(0,0, player.getWorkerRef().get(1)));
        assertTrue(player.addWorker(4,4, player.getWorkerRef().get(1)));
    }
}