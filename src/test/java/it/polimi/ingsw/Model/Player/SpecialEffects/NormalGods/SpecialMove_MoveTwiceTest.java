package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMove_MoveTwiceTest {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();

    @Test
    public void testSpecialMove_MoveTwice() {
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        mockWorkers1.add(worker1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(board.getGrid()[0][0]);
        board.getGrid()[0][0].setWorker(worker1);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialMove_MoveTwice(mockPlayer);


        player.setEnableSpecialMove(true);
        assertTrue(player.move(1,1, 2, 2, worker1));
        player.setEnableSpecialMove(false);
        assertFalse(player.move(1, 1, 3, 3, worker1));
        assertEquals(worker1.getCurCell(), worker1.getPlayerWorker().getBoard().getGrid()[2][2]);

        player.setEnableSpecialMove(true);
        assertFalse(player.move(1,1, 2, 2, worker1));

        assertEquals(worker1.getCurCell(), worker1.getPlayerWorker().getBoard().getGrid()[2][2]);

        worker1.getPlayerWorker().getBoard().getGrid()[4][4].setDome(true);

        player.setEnableSpecialMove(true);
        assertTrue(player.move(3,3, 4, 4, worker1));

        assertEquals(worker1.getCurCell(), worker1.getPlayerWorker().getBoard().getGrid()[3][3]);



        /*player.move(0,0, worker1);
        assertFalse(player.move(0, 2, worker1));
        player.move(0,0, worker1);


        //enable specialMove
        player.setEnableSpecialMove(true);
        //assertTrue(player.move(2, 2, worker1));
        assertFalse(player.move(2, 2, worker1));
        assertTrue(player.move(1, 1, worker1));
        assertFalse(player.move(4, 4, worker1));*/


    }
}