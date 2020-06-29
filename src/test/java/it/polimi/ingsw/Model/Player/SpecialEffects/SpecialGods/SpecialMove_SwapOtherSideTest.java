package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialMove_SwapOtherSideTest {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();
    private final List<Worker> mockWorkers2 = new ArrayList<>();

    @Test
    public void testSpecialMove_SwapWorkers() {
        //Correct Initialization of two workers
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.setCurCell(board.getGrid()[3][3]);
        worker2.setCurCell(board.getGrid()[4][4]);
        PlayerInterface player1 = new SpecialMove_SwapOtherSide(new Player("Rexo", mockWorkers1, board));
        PlayerInterface player2 = new Player("mockName", mockWorkers2, board);
        worker1.setPlayerWorker(player1);
        worker2.setPlayerWorker(player2);
        player1.getBoard().getGrid()[3][3].setWorker(worker1);
        player2.getBoard().getGrid()[4][4].setWorker(worker2);
        player1.setEnableSpecialMove(false);
        assertEquals(worker1, player1.getBoard().getGrid()[3][3].getWorker());

        player1.setEnableSpecialMove(true);
        player1.move(4,4,4,3,worker1);
        assertEquals(worker2, player1.getBoard().getGrid()[2][2].getWorker());
        assertEquals(worker1, player1.getBoard().getGrid()[4][3].getWorker());

    }



/*
    @Test
    public void testSpecialMove_SwapWorkers4() {

        int row1=4;
        int col1=4;
        int row2=5;
        int col2=5;

        int rowMove=5;
        int colMove=4;

        int rowPushed=3;
        int colPushed=3;

        //Correct Initialization of two workers
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.setCurCell(board.getGrid()[row1][col1]);
        worker2.setCurCell(board.getGrid()[row2][col2]);
        PlayerInterface player1 = new SpecialMove_SwapOtherSide(new Player("1", mockWorkers1, board));
        PlayerInterface player2 = new Player("mockName", mockWorkers2, board);
        worker1.setPlayerWorker(player1);
        worker2.setPlayerWorker(player2);
        player1.getBoard().getGrid()[row1][col1].setWorker(worker1);
        player2.getBoard().getGrid()[row2][col2].setWorker(worker2);

        //special move

        player1.setEnableSpecialMove(true);
        player1.move(row2, col2, rowMove, colMove, worker1);
        assertEquals(worker1, player1.getBoard().getGrid()[rowMove][colMove].getWorker());
        assertEquals(worker2, player1.getBoard().getGrid()[rowPushed][colPushed].getWorker());

    }

 */
}

