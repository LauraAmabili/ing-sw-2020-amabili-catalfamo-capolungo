package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;

/*

Minotaur

Your Worker may move into an opponent Worker’s space, if their Worker can be
forced one space straight backwards to an unoccupied space at any level.

*/



public class SpecialMove3 extends PlayerDecorator {

    // constructor
    public SpecialMove3(PlayerInterface p){
        super(p);
    }

    public boolean move(int row, int col, Worker worker){
        worker.getCurCell().setWorker(null);
        worker.setOldCell(worker.getCurCell());
        worker.setCurCell(worker.getBoard().getGrid()[row][col]);
        worker.getCurCell().setWorker(worker);

        // effect activation call specialEffect()
        return false;
    }


    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect) {
        if (specialEffect) {
            List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
            adj.removeIf((n) -> n.getWorker().getPlayerWorker() == worker.getPlayerWorker());
            adj.removeIf(BoardCell::getDome);
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
            return adj;
        }
        return null;
    }

}