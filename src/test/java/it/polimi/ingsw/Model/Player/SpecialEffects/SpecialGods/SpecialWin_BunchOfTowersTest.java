package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods.SpecialWin_MoveDown;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpecialWin_BunchOfTowersTest {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();
    private final List<Worker> mockWorkers2 = new ArrayList<>();


    @Test
    public void testSpecialWin_MoveDown() {

        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        BoardCell cell = new BoardCell(0, 0);



        mockWorkers1.add(worker1);

        worker1.setCurCell(board.getGrid()[0][0]);
        worker1.setOldCell(board.getGrid()[0][1]);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        mockPlayer1.getBoard().getGrid()[0][0].setWorker(worker1);
        mockWorkers1.add(worker1);
        PlayerInterface player1 = new SpecialWin_BunchOfTowers(mockPlayer1);
        /*board.getGrid()[0][1].setLevel(2); //old
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
        assertFalse(player1.checkWin(worker1));*/

        for (int i=0; i<4; i++){
            board.getGrid()[0][i].setLevel(3);
            board.getGrid()[0][i].setDome(true);

        }
        assertFalse(player1.checkWin(worker1));

        board.getGrid()[4][0].setLevel(3);
        board.getGrid()[4][0].setDome(true);

        assertTrue(player1.checkWin(worker1));

        for (int i=0; i<4; i++){
            board.getGrid()[i][0].setLevel(3);
            board.getGrid()[i][0].setDome(true);
            assertTrue(player1.checkWin(worker1));

        }


    }


}