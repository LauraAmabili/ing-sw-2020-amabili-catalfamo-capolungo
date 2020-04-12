package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseMove extends PlayerDecorator{

    protected PlayerInterface player;

    public BaseMove(PlayerInterface player) {
        super(player);
    }

    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n)-> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        adj.removeIf((n)-> (n.getLevel() > worker.getCurCell().getLevel()+1));
        return adj;
    }

    public boolean move(int row, int col, @NotNull Worker worker) {
        if(availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(worker.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }

}
