package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;


public class SpecialMove_BMB extends PlayerDecorator {

    public SpecialMove_BMB(PlayerInterface p) {
        super(p);
    }

    /**
     * @param row
     * @param col
     * @param worker
     * @param specialEffect
     * @param rowBuild
     * @param colBuild
     * @return
     */
    @Override
    public boolean move(int row, int col, @NotNull Worker worker, boolean specialEffect, int rowBuild, int colBuild) {
        if (specialEffect){
            build(rowBuild, colBuild, worker);
        }
        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(this.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }




}