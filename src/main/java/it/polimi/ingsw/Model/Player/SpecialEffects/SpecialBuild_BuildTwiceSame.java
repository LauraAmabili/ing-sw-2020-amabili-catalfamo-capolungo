package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class SpecialBuild_BuildTwiceSame extends PlayerDecorator {

    public SpecialBuild_BuildTwiceSame(PlayerInterface p) {
        super(p);
    }

    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }

    private boolean enableSpecialBuild;

    /**Builds twice on the same BoardCell
     * @param row BoardCell row
     * @param col BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */

    public boolean build(int row, int col, @NotNull Worker worker) {
        if (enableSpecialBuild) {
            if (availableCellsToBuild(worker, true).contains(this.getBoard().getGrid()[row][col])) {
                BoardCell b = this.getBoard().getGrid()[row][col];
                b.setLevel((b.getLevel() + 2));
                return true;
            }
        }
        return player.build(row, col, worker);
    }


    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect) {
        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        adj.removeIf((n) -> n.getLevel() == 2);
        return adj;

    }


}