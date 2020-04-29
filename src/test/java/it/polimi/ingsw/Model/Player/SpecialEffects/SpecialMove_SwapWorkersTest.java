package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialMove_SwapWorkers;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMove_SwapWorkersTest {


    private List<Worker> mockWorkers1 = new ArrayList<Worker>();
    private List<Worker> mockWorkers2 = new ArrayList<Worker>();

    @Test
    public void testSpecialMove_SwapWorkers() {

        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        BoardCell cell = new BoardCell(0, 0);

        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker2.setCurCell(board.getGrid()[1][1]);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        worker1.setPlayerWorker(mockPlayer1);
        mockWorkers1.add(worker1);
        PlayerInterface player1 = new SpecialMove_SwapWorkers(mockPlayer1);
        Player mockPlayer2 = new Player("mockName", mockWorkers2, board);
        mockWorkers1.add(worker2);
        worker2.setPlayerWorker(mockPlayer2);
        mockPlayer1.getBoard().getGrid()[0][0].setWorker(worker1);
        mockPlayer2.getBoard().getGrid()[1][1].setWorker(worker2);
        player1.move(1, 1, worker1);
        assertEquals(worker1.getCurCell(), mockPlayer1.getBoard().getGrid()[1][1]);
        assertEquals(worker2.getCurCell(), mockPlayer2.getBoard().getGrid()[0][0]);

    }
}