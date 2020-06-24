package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods.SpecialMove_SwapWorkers;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
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
        worker1.setCurCell(board.getGrid()[0][0]);
        worker2.setCurCell(board.getGrid()[1][1]);
        PlayerInterface player1 = new SpecialMove_SwapOtherSide(new Player("Rexo", mockWorkers1, board));
        PlayerInterface player2 = new Player("mockName", mockWorkers2, board);
        worker1.setPlayerWorker(player1);
        worker2.setPlayerWorker(player2);
        player1.getBoard().getGrid()[0][0].setWorker(worker1);
        player2.getBoard().getGrid()[1][1].setWorker(worker2);
        assertEquals(worker1, player1.getBoard().getGrid()[0][0].getWorker());
        assertEquals(worker2, player2.getBoard().getGrid()[1][1].getWorker());
        assertEquals(worker1.getCurCell(), player1.getBoard().getGrid()[0][0]);
        assertEquals(worker2.getCurCell(), player2.getBoard().getGrid()[1][1]);

        //normal moves



        player1.move(0, 1, worker1);
        assertNotEquals(worker1, player1.getBoard().getGrid()[0][0].getWorker());
        assertEquals(worker1, player1.getBoard().getGrid()[0][1].getWorker());
        player2.move(0, 2, worker2);
        assertNotEquals(worker2, player1.getBoard().getGrid()[1][1].getWorker());
        assertEquals(worker2, player1.getBoard().getGrid()[0][2].getWorker());

        //special move

        player1.move(0, 2, worker1);
        assertEquals(worker1, player1.getBoard().getGrid()[0][2].getWorker());
        assertEquals(worker2, player1.getBoard().getGrid()[0][0].getWorker());
        for (int i = 0; i< Board.getSIZE(); i++)
            for (int j = 0; j< Board.getSIZE(); j++){
                if (i!=0 && j!=2)
                    assertNotEquals(worker1, player1.getBoard().getGrid()[i][j].getWorker());
                if (i!=0 && j!=0)
                    assertNotEquals(worker2, player1.getBoard().getGrid()[i][j].getWorker());
            }

    }

    @Test
    public void testSpecialMove_SwapWorkers2() {

        //Correct Initialization of two workers
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.setCurCell(board.getGrid()[2][2]);
        worker2.setCurCell(board.getGrid()[1][1]);
        PlayerInterface player1 = new SpecialMove_SwapOtherSide(new Player("Rexo", mockWorkers1, board));
        PlayerInterface player2 = new Player("mockName", mockWorkers2, board);
        worker1.setPlayerWorker(player1);
        worker2.setPlayerWorker(player2);
        player1.getBoard().getGrid()[2][2].setWorker(worker1);
        player2.getBoard().getGrid()[1][1].setWorker(worker2);
        assertEquals(worker1, player1.getBoard().getGrid()[2][2].getWorker());
        assertEquals(worker2, player2.getBoard().getGrid()[1][1].getWorker());
        assertEquals(worker1.getCurCell(), player1.getBoard().getGrid()[2][2]);
        assertEquals(worker2.getCurCell(), player2.getBoard().getGrid()[1][1]);


        //special move

        player1.move(1, 1, worker1);
        assertEquals(worker1, player1.getBoard().getGrid()[1][1].getWorker());
        assertEquals(worker2, player1.getBoard().getGrid()[3][3].getWorker());
        for (int i = 0; i< Board.getSIZE(); i++)
            for (int j = 0; j< Board.getSIZE(); j++){
                if (i!=1 && j!=1)
                    assertNotEquals(worker1, player1.getBoard().getGrid()[i][j].getWorker());
                if (i!=3 && j!=3)
                    assertNotEquals(worker2, player1.getBoard().getGrid()[i][j].getWorker());
            }

    }

    @Test
    public void testSpecialMove_SwapWorkers3() {

        int row1=1;
        int col1=1;
        int row2=2;
        int col2=2;

        int rowPushed=0;
        int colPushed=0;

        //Correct Initialization of two workers
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.setCurCell(board.getGrid()[row1][col1]);
        worker2.setCurCell(board.getGrid()[row2][col2]);
        PlayerInterface player1 = new SpecialMove_SwapOtherSide(new Player("Rexo", mockWorkers1, board));
        PlayerInterface player2 = new Player("mockName", mockWorkers2, board);
        worker1.setPlayerWorker(player1);
        worker2.setPlayerWorker(player2);
        player1.getBoard().getGrid()[row1][col1].setWorker(worker1);
        player2.getBoard().getGrid()[row2][col2].setWorker(worker2);

        //special move

        player1.move(row2, col2, worker1);
        assertEquals(worker1, player1.getBoard().getGrid()[row2][col2].getWorker());
        assertEquals(worker2, player1.getBoard().getGrid()[rowPushed][colPushed].getWorker());


    }

    @Test
    public void testSpecialMove_SwapWorkers4() {

        int row1=0;
        int col1=1;
        int row2=0;
        int col2=0;

        int rowPushed=0;
        int colPushed=2;

        //Correct Initialization of two workers
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        worker1.setCurCell(board.getGrid()[row1][col1]);
        worker2.setCurCell(board.getGrid()[row2][col2]);
        PlayerInterface player1 = new SpecialMove_SwapOtherSide(new Player("Rexo", mockWorkers1, board));
        PlayerInterface player2 = new Player("mockName", mockWorkers2, board);
        worker1.setPlayerWorker(player1);
        worker2.setPlayerWorker(player2);
        player1.getBoard().getGrid()[row1][col1].setWorker(worker1);
        player2.getBoard().getGrid()[row2][col2].setWorker(worker2);

        //special move

        player1.move(row2, col2, worker1);
        assertEquals(worker1, player1.getBoard().getGrid()[row2][col2].getWorker());
        assertEquals(worker2, player1.getBoard().getGrid()[rowPushed][colPushed].getWorker());


    }




}