package it.polimi.ingsw;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.Worker;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Board board = new Board();
        ArrayList<Worker> list = new ArrayList<>();
        Worker worker1 = new Worker(1, board);
        worker1.setCurCell(board.getGrid()[0][0]);
        Worker worker2 = new Worker(2, board);
        worker2.setCurCell(board.getGrid()[0][1]);
        list.add(worker1);
        list.add(worker2);
        Player player = new Player("SuperRexo", list);
        board.deleteWorkers(player);
    }
}
