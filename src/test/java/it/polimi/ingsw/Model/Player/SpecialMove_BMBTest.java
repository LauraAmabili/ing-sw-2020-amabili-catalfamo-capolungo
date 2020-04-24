package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMove_BMBTest {

    private List<Worker> mockWorkers1 = new ArrayList<Worker>();


    @Test
    public void testSpecialMove_BMB() {
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        mockWorkers1.add(worker1);
        Board board = new Board();
        BoardCell cell = new BoardCell(0,0);
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(cell);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialMove_BMB(mockPlayer);
        player.move(0, 1, worker1, true, 1, 0);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(worker1, board.getGrid()[0][1].getWorker());

    }

}