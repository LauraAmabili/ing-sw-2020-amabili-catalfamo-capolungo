package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class SpecialMove_MoveTwice extends PlayerDecorator {


    public SpecialMove_MoveTwice(PlayerInterface p) {
        super(p);
    }

    /**
     * Standard Move
     *
     * @param row
     * @param col
     * @param worker
     * @return
     */
    public boolean move(int row, int col, @NotNull Worker worker) {
        if (availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(worker.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }


    /**
     * returns the BoardCells such that:
     * - distance(worker)<=2
     * - there are not Workers
     * - there are not Domes
     * - if (moveUp), remove BoardCells such that BoardCell.level+1>Worker.level
     * - if !(moveUp), remove BoardCells such that BoardCell.level>Worker.level
     * @param worker Worker used
     * @return ArrayList with the BoardCells
     */
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        for (BoardCell x : worker.getBoard().adjacentCells(worker.getCurCell())) {
            adj.addAll(worker.getBoard().adjacentCells(x));
        }
        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        if (worker.getPlayerWorker().isMoveUp()) {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        } else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }
        return adj;

    }


}