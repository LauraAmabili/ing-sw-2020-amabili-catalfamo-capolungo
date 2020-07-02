package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class SpecialMove_BMBTest {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();


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
        PlayerInterface player = new SpecialMove_BMB(mockPlayer);
        player.setEnableSpecialMove(true);
        player.availableCellsToMove(mockPlayer.getWorkerRef().get(0));
        player.move(1, 0, 0, 1, worker1);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(worker1, board.getGrid()[0][1].getWorker());
        player.setEnableSpecialMove(false);
        player.move(1,0, worker1);
        assertEquals(worker1, board.getGrid()[1][0].getWorker());
        player.setHasSpecialMove(true);
        assertTrue(player.isHasSpecialMove());
        player.setEnableSpecialMove(true);
        assertTrue(player.isEnableSpecialMove());
        assertFalse(mockPlayer.isHasTwoInputMove());

    }

}