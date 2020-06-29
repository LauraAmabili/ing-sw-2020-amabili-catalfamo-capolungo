package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class SpecialMove_MoveTwice extends PlayerDecorator {

    private final boolean hasSpecialMove;

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

    public SpecialMove_MoveTwice(PlayerInterface p) {
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
    @Override
    public synchronized boolean move(int row, int col, @NotNull Worker worker) {
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
        return player.availableCellsToMove(worker, false);

    }


}