package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods.SpecialMove_SwapOtherSide;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

class SpecialWin_MoveDownTest {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();
    private final List<Worker> mockWorkers2 = new ArrayList<>();


    @Test
    public void testSpecialWin_MoveDown() {


        Worker worker1 = new Worker(1);
        Board board = new Board();
        mockWorkers1.add(worker1);


        //set level

        board.getGrid()[0][0].setLevel(3);
        board.getGrid()[1][1].setLevel(3);
        worker1.setCurCell(board.getGrid()[0][0]);
        PlayerInterface player1 = new SpecialWin_MoveDown(new Player("Rexo", mockWorkers1, board));
        worker1.setPlayerWorker(player1);
        player1.getBoard().getGrid()[0][0].setWorker(worker1);
        assertEquals(worker1, player1.getBoard().getGrid()[0][0].getWorker());
        assertEquals(worker1.getCurCell(), player1.getBoard().getGrid()[0][0]);




        player1.move(1, 1, worker1);
        assertFalse(player1.checkWin(worker1));

        player1.move(0, 1, worker1);
        assertTrue(player1.checkWin(worker1));








    }


}