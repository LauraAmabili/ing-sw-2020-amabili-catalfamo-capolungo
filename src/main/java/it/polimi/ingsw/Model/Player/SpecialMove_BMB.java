package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;


/*Artemis

Your Worker may move one additional time, but not back to its initial space.

*/


public class SpecialMove_BMB extends PlayerDecorator {

    // constructor
    public SpecialMove_BMB(PlayerInterface p) {
        super(p);
    }

    @Override
    public boolean move(int row, int col, @NotNull Worker worker, boolean specialEffect, int rowBuild, int colBuild) {
        if (specialEffect){
            build(rowBuild, colBuild, worker);
        }
        if (availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(worker.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }




}