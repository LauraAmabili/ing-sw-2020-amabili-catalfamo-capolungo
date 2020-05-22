package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialBuild_DomeAnyLevel;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialBuild_DomeAnyLevelTest {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();

    @Test
    public void testSpecialBuild_DomeAnyLevel() {
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
        PlayerInterface player = new SpecialBuild_DomeAnyLevel(mockPlayer);
        player.setEnableSpecialBuild(false);
        player.build(1, 0, worker1);
        assertFalse(board.getGrid()[1][0].getDome());
        assertEquals(1, board.getGrid()[1][0].getLevel());
        player.setEnableSpecialBuild(true);
        player.build(1, 0, worker1);
        assertTrue(board.getGrid()[1][0].getDome());
        assertEquals(1, board.getGrid()[1][0].getLevel());

    }


}