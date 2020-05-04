package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialMove_MoveTwice;
import it.polimi.ingsw.Model.PlayerFSA.AddNickname;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMove_MoveTwiceTest {

    Game game = new Game();
    private List<Worker> mockWorkers1 = new ArrayList<Worker>();

    @Test
    public void testSpecialMove_MoveTwice() {
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
        mockPlayer.setPlayerState(new AddNickname(mockPlayer, game));
        PlayerInterface player = new SpecialMove_MoveTwice(mockPlayer);
        assertEquals(worker1.getCurCell(), cell);
        assertFalse(player.move(0, 3, worker1));
        assertSame(null, board.getGrid()[0][3].getWorker());
        assertTrue(player.move(2, 2, worker1));
        assertSame(worker1, board.getGrid()[2][2].getWorker());

    }
}