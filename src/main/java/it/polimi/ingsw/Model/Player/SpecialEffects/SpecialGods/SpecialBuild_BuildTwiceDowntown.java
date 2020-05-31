package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;


public class SpecialBuild_BuildTwiceDowntown extends PlayerDecorator {

    private final boolean hasSpecialBuild;
    private final boolean hasTwoInputBuild;

    private boolean enableSpecialBuild;

    @Override
    public boolean isHasSpecialBuild() {
        return hasSpecialBuild;
    }

    @Override
    public boolean isHasTwoInputBuild() {
        return hasTwoInputBuild;
    }

    @Override
    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    @Override
    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }

    public SpecialBuild_BuildTwiceDowntown(PlayerInterface p) {
        super(p);
        hasSpecialBuild = true;
        hasTwoInputBuild = true;
    }

    /**Builds on two different BoardCells
     * @param row1 First BoardCell row
     * @param col1 First BoardCell col
     * @param worker Worker used
     * @param row2 Second BoardCell row
     * @param col2 Second BoardCell col
     * @return true <--> the method works </-->
     */
    @Override
    public boolean build(int row1, int col1, int row2, int col2, @NotNull Worker worker) {

        BoardCell b1 = this.getBoard().getGrid()[row1][col1];
        BoardCell b2 = this.getBoard().getGrid()[row2][col2];
        if (enableSpecialBuild) {
            if(!b1.equals(b2) && downtown(b2)) {
                return player.build(row1, col1, worker) && player.build(row2, col2, worker);
            }
            return false;
        }
        return player.build(row1, col1, worker);
    }

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
