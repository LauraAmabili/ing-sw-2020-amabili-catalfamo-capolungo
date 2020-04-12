package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseBuild extends PlayerDecorator{

    protected PlayerInterface player;

    public BaseBuild(PlayerInterface player) {
        super(player);
    }

    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n)-> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        return adj;

    }

    public boolean build(int row, int col, @NotNull Worker worker) {
        if(availableCellsToBuild(worker).contains(worker.getBoard().getGrid()[row][col])) {
            BoardCell b = worker.getBoard().getGrid()[row][col];
            if(b.getLevel() == 3) {
                b.setDome(true);
            } else {
                b.setLevel((b.getLevel() + 1));
            }
            return true;
        }
        return false;
    }

}
