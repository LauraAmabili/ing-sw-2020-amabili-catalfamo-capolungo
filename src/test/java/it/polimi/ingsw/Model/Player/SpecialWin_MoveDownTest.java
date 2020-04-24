package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialWin_MoveDownTest {


    private List<Worker> mockWorkers1 = new ArrayList<Worker>();
    private List<Worker> mockWorkers2 = new ArrayList<Worker>();


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


}