package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    Game game = new Game();
    ArrayList<BoardCell> adjCells = new ArrayList<>();


    @Before
    public void setUp() {

    }
/*
    @Test
    public void testAvailableCellsToMove() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
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
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
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
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
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
        worker1.setCurCell(worker1.getBoard().getGrid()[1][2]);
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


    //SPECIAL MOVES

    @Test
    public void testSpecialBuild_BuildTwiceDifferent() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialBuild_BuildTwiceDifferent(mockPlayer);
        player.build(1, 0, worker1, 0, 1);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(1, board.getGrid()[0][1].getLevel());

    }

    @Test
    public void testSpecialBuild_BuildTwiceSame() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialBuild_BuildTwiceSame(mockPlayer);

        player.build(1, 0, worker1, true);
        assertEquals(2, board.getGrid()[1][0].getLevel());

    }

    @Test
    public void testSpecialBuild_DomeAnyLevel() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialBuild_DomeAnyLevel(mockPlayer);
        player.build(1, 0, worker1, true);
        assertTrue(board.getGrid()[1][0].getDome());
    }

    @Test
    public void testSpecialMove_BMB() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialMove_BMB(mockPlayer);
        player.move(0, 1, worker1, true, 1, 0);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(worker1, board.getGrid()[0][1].getWorker());

    }

    @Test
    public void testSpecialMove_MoveTwice() {

        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(worker1.getBoard().getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialMove_MoveTwice(mockPlayer);
        assertEquals(worker1.getCurCell(), worker1.getBoard().getGrid()[0][0]);
        assertFalse(player.move(0, 3, worker1));
        assertSame(null, board.getGrid()[0][3].getWorker());
        assertTrue(player.move(2, 2, worker1));
        assertSame(worker1, board.getGrid()[2][2].getWorker());

    }


        @Test
        public void testSpecialMove_PushOpponent() {

        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.getBoard().getGrid()[0][0].setWorker(worker1);
        worker1.getBoard().getGrid()[0][1].setWorker(worker2);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker2.setCurCell(board.getGrid()[0][1]);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        worker1.setPlayerWorker(mockPlayer1);
        mockWorkers1.add(worker1);
        PlayerInterface player1 = new SpecialMove_PushOpponent(mockPlayer1);
        Player mockPlayer2 = new Player("mockName", mockWorkers2, board);
        mockWorkers1.add(worker2);
        worker2.setPlayerWorker(mockPlayer2);
        player1.move(0, 1, worker1);
        assertEquals(worker1.getCurCell(), worker1.getBoard().getGrid()[0][1]);
        assertEquals(worker2.getCurCell(), worker2.getBoard().getGrid()[0][2]);

    }


    @Test
    public void testSpecialMove_SwapWorkers() {

        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.getBoard().getGrid()[0][0].setWorker(worker1);
        worker1.getBoard().getGrid()[1][1].setWorker(worker2);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker2.setCurCell(board.getGrid()[1][1]);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        worker1.setPlayerWorker(mockPlayer1);
        mockWorkers1.add(worker1);
        PlayerInterface player1 = new SpecialMove_SwapWorkers(mockPlayer1);
        Player mockPlayer2 = new Player("mockName", mockWorkers2, board);
        mockWorkers1.add(worker2);
        worker2.setPlayerWorker(mockPlayer2);
        player1.move(1, 1, worker1);
        assertEquals(worker1.getCurCell(), worker1.getBoard().getGrid()[1][1]);
        assertEquals(worker2.getCurCell(), worker2.getBoard().getGrid()[0][0]);

    }


    @Test
    public void testSpecialOpponentTurn_LockMoveUp() {

        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.getBoard().getGrid()[0][0].setWorker(worker1);
        worker1.getBoard().getGrid()[0][1].setWorker(worker2);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker2.setCurCell(board.getGrid()[0][1]);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        worker1.setPlayerWorker(mockPlayer1);
        mockWorkers1.add(worker1);
        PlayerInterface player1 = new SpecialOpponentTurn_LockMoveUp(mockPlayer1);
        Player mockPlayer2 = new Player("mockName", mockWorkers2, board);
        mockWorkers1.add(worker2);
        worker2.setPlayerWorker(mockPlayer2);
        assertTrue(player1.isMoveUp() && mockPlayer2.isMoveUp());
        board.getGrid()[1][1].setLevel(0);
        board.getGrid()[1][1].setLevel(1);
        player1.move(1, 1, worker1);
        assertEquals(worker2, worker1.getBoard().getGrid()[0][1].getWorker());
        assertFalse(mockPlayer2.isMoveUp());
        assertTrue(player1.isMoveUp());
        player1.move(0, 0, worker1);
        assertTrue(player1.isMoveUp() && mockPlayer2.isMoveUp());

    }

    @Test
    public void testSpecialWin_MoveDown() {

        mockWorkers1.add(worker1);
        worker1.getBoard().getGrid()[0][0].setWorker(worker1);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker1.setOldCell(board.getGrid()[0][1]);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        mockWorkers1.add(worker1);
        PlayerInterface player1 = new SpecialWin_MoveDown(mockPlayer1);
        board.getGrid()[0][1].setLevel(2); //old
        board.getGrid()[0][0].setLevel(2); //cur
        assertFalse(player1.checkWin(worker1));
        board.getGrid()[0][1].setLevel(2); //old
        board.getGrid()[0][0].setLevel(3); //cur
        assertTrue(player1.checkWin(worker1));
        board.getGrid()[0][1].setLevel(3); //old
        board.getGrid()[0][0].setLevel(0); //cur
        assertTrue(player1.checkWin(worker1));
        board.getGrid()[0][1].setLevel(3); //old
        board.getGrid()[0][0].setLevel(2); //cur
        assertFalse(player1.checkWin(worker1));

    }


 */
}