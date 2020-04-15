package it.polimi.ingsw.Model.Player;


/*
Athena

If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn.
*/

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialOpponentTurn_LockMoveUp extends PlayerDecorator {

    protected PlayerInterface player;

    private boolean toUnlock = false;

    // constructor
    public SpecialOpponentTurn_LockMoveUp(PlayerInterface player) {
        super(player);
    }


    @Override
    public boolean move(int row, int col, @NotNull Worker worker) {
        if (toUnlock)
            unlockMoveUp(worker);
        if (availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(worker.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            if (worker.getCurCell().getLevel() > worker.getOldCell().getLevel())
                lockMoveUp(worker);
            return true;
        }
        return false;
    }

    //for each player -> player.moveUp=false
    public void lockMoveUp(Worker worker) {
        BoardCell[][] grid = worker.getBoard().getGrid();
        for (BoardCell[] boardCells : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (boardCells[j].getWorker() != null) {
                    if (boardCells[j].getWorker().getPlayerWorker() != worker.getPlayerWorker()) {
                        boardCells[j].getWorker().getPlayerWorker().setMoveUp(false);
                    }
                }
            }
        }
        toUnlock = true;
    }

    //for each player -> player.moveUp=true
    public void unlockMoveUp(Worker worker) {
        BoardCell[][] grid = worker.getBoard().getGrid();
        for (BoardCell[] boardCells : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (boardCells[j].getWorker() != null) {
                    if (boardCells[j].getWorker().getPlayerWorker() != worker.getPlayerWorker()) {
                        boardCells[j].getWorker().getPlayerWorker().setMoveUp(true);
                    }
                }
            }
        }
        toUnlock = false;
    }


}
	


