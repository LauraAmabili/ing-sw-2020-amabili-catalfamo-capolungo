package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;





public class SpecialMove_MoveTwice extends PlayerDecorator {

    private final boolean hasSpecialMove;
    private final boolean hasTwoInputMove;

    private boolean enableSpecialMove;

    @Override
    public boolean isHasTwoInputMove() {
        return hasTwoInputMove;
    }

    @Override
    public boolean isHasSpecialMove() {
        return hasSpecialMove;
    }

    @Override
    public boolean isEnableSpecialMove() {
        return enableSpecialMove;
    }

    @Override
    public void setEnableSpecialMove(boolean enableSpecialMove) {
        this.enableSpecialMove = enableSpecialMove;
    }


    public SpecialMove_MoveTwice(PlayerInterface p) {
        super(p);
        hasSpecialMove = true;
        hasTwoInputMove = true;
    }

    /**
     * Double move
     *
     * @param row BoardCell row
     * @param col BoardCell col
     * @param worker Worker used
     * @return  * @return true <--> the method works </-->
     */
    @Override
    public synchronized boolean move(int rowBuild, int colBuild, int row, int col, @NotNull Worker worker) {
        BoardCell oldCell = worker.getCurCell();
        BoardCell cell1 = getBoard().getGrid()[rowBuild][colBuild];
        BoardCell cell2 = getBoard().getGrid()[row][col];
        if (!isMoveUp()){
            if (cell1.getLevel()>worker.getCurCell().getLevel())
                return false;
            if (cell2.getLevel()>worker.getCurCell().getLevel())
                return false;
        }
        if (oldCell.equals(cell2)){
            return false;
        }
        if (enableSpecialMove) {
            if (availableCellsToMove(worker).contains(cell1))
                if (player.move(rowBuild, colBuild, worker)){
                    if (availableCellsToMove(worker).contains(cell2))
                        {
                            return player.move(row, col, worker);
                        }
                    return true;
                }

        }
        return false;
    }

}