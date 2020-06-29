package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_MoveTwicePerimetral extends PlayerDecorator {

    private boolean hasSpecialMove = true;

    @Override
    public boolean isEnableSpecialMove() {
        return enableSpecialMove;
    }

    @Override
    public void setEnableSpecialMove(boolean enableSpecialMove) {
        this.enableSpecialMove = enableSpecialMove;
    }

    @Override
    public boolean isHasSpecialMove() {
        return hasSpecialMove;
    }

    private boolean enableSpecialMove;

    public SpecialMove_MoveTwicePerimetral(PlayerInterface p) {
        super(p);
        hasSpecialMove = true;
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
        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(this.getBoard().getGrid()[row][col]);
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
    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        if(enableSpecialMove) {
            List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
            for (BoardCell x : this.getBoard().adjacentCells(worker.getCurCell())) {
                if (!downtown(x))
                    adj.addAll(this.getBoard().adjacentCells(x));
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
        return player.availableCellsToMove(worker);

    }


    /**
     * Checks where he cannot build
     * @param boardCell boardcell not to build on
     * @return boolean
     */
    private boolean downtown(BoardCell boardCell) {
        int row = boardCell.getRow();
        int col = boardCell.getCol();
        if (row==0)
            return false;
        if (row==4)
            return false;
        if (col==0)
            return false;
        if (col==4)
            return false;
        return true;
    }


















































































































































































































    
}