package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    final Board board = new Board();
    final Game game = new Game();

    @Test
    public void initialiseGrid() {
        Assert.assertNotNull(board.grid);
    }

    @Test
    public void deleteWorkers() {
        ArrayList<Worker> list = new ArrayList<>();
        Worker worker1 = new Worker(1);
        worker1.setCurCell(board.getGrid()[0][0]);
        Worker worker2 = new Worker(2);
        worker2.setCurCell(board.getGrid()[0][1]);
        list.add(worker1);
        list.add(worker2);
        Player player = new Player("SuperRexo", list, board);
        board.deleteWorkers(player);
        for (int i = player.getWorkerRef().size() - 1; i > 0; i--) {
            Assert.assertNull(player.getWorkerRef().get(i));
        }
    }

    @Test
    public void adjacentCells() {
        ArrayList<BoardCell> b = new ArrayList<>();
        Worker worker = new Worker(1);
        worker.setCurCell(board.getGrid()[3][3]);
        b.add(board.getGrid()[2][2]);
        b.add(board.getGrid()[2][3]);
        b.add(board.getGrid()[2][4]);
        b.add(board.getGrid()[3][2]);
        b.add(board.getGrid()[3][4]);
        b.add(board.getGrid()[4][2]);
        b.add(board.getGrid()[4][3]);
        b.add(board.getGrid()[4][4]);
        Assert.assertEquals(board.adjacentCells(worker.getCurCell()), b);
    }

    @Test
    public void freeCells() {
        Worker worker1 = new Worker(1);
        Worker worker2 = new Worker(2);
        ArrayList<BoardCell> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                list.add(board.getGrid()[i][j]);
            }
        }
        list.remove(board.getGrid()[3][3]);
        list.remove(board.getGrid()[0][0]);
        board.getGrid()[3][3].setWorker(worker1);
        board.getGrid()[0][0].setWorker(worker2);
        Assert.assertEquals(board.freeCells(), list);
    }

    @Test
    public void squareBoard() throws IOException, InterruptedException {
        game.initialiseMatch(2);
        game.createTurn();
        game.getStateList().get(0).addNickname("Notateen");
        game.getStateList().get(1).addNickname("SuperRexo");
        board.printGrid();

    }

    @Test
    public void printGrid() {
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        int counter = 0;
        List<PlayerInterface> list = new ArrayList<>();
        List<Worker> workerList = new ArrayList<>();
        PlayerInterface player1 = new Player();
        player1.setBoard(board);
        player1.setNickname("SuperRexo");
        player1.setColor(ANSI_YELLOW);
        PlayerInterface player2 = new Player();
        player2.setBoard(board);
        player2.setColor(ANSI_BLUE);
        player2.setNickname("NotATeen");
        list.add(player1);
        list.add(player2);
        for(PlayerInterface p : list) {
            for (int i = 0; i < 2; i++, counter++) {
                Worker worker = new Worker(counter);
                workerList.add(worker);
                worker.setPlayerWorker(p);
                worker.setColor(p.getColor());
            }
            p.setWorkerRef(workerList);
            workerList.clear();
        }
        board.getGrid()[0][0].setWorker(player1.getWorkerRef().get(0));
        player1.getWorkerRef().get(0).setCurCell(board.getGrid()[0][0]);
        board.getGrid()[4][4].setWorker(player1.getWorkerRef().get(1));
        player1.getWorkerRef().get(1).setCurCell(board.getGrid()[4][4]);
        board.getGrid()[1][1].setWorker(player2.getWorkerRef().get(0));
        player2.getWorkerRef().get(0).setCurCell(board.getGrid()[1][1]);
        board.getGrid()[3][3].setWorker(player2.getWorkerRef().get(1));
        player2.getWorkerRef().get(1).setCurCell(board.getGrid()[3][3]);
        board.getGrid()[2][2].setDome(true);
        board.getGrid()[1][4].setLevel(3);
        board.getGrid()[2][4].setDome(true);
        board.getGrid()[2][4].setLevel(3);
        board.printGrid();
        board.printAvailableGrid(player1.availableCellsToMove(player1.getWorkerRef().get(0)));
    }

}