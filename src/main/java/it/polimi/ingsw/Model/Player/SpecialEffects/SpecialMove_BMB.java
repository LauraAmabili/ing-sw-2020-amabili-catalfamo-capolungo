package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_BMB extends PlayerDecorator {

    public SpecialMove_BMB(PlayerInterface p) {
        super(p);
    }

    /**Build, move on the same level, build
     * @param row First BoardCell row
     * @param col First BoardCell col
     * @param worker Worker used
     * @param specialEffect SpecialEffect enabled
     * @param rowBuild Second BoardCell row
     * @param colBuild Second BoardCell col
     * @return true <--> the method works </-->
     */
    @Override
    public boolean move(int row, int col, @NotNull Worker worker, boolean specialEffect, int rowBuild, int colBuild) {
        if (specialEffect){
            build(rowBuild, colBuild, worker);
        }
        if (availableCellsToMoveAfterEffect(worker).contains(this.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(this.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }

    public List<BoardCell> availableCellsToMoveAfterEffect(@NotNull Worker worker) {
        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        return adj;
    }

}