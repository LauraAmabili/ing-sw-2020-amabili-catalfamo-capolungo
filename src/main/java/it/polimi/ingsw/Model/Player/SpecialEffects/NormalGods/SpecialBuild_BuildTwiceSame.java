package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class SpecialBuild_BuildTwiceSame extends PlayerDecorator {

    private final boolean hasSpecialBuild;

    private boolean enableSpecialBuild;

    @Override
    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    @Override
    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }

    @Override
    public boolean isHasSpecialBuild() {
        return hasSpecialBuild;
    }

    public SpecialBuild_BuildTwiceSame(PlayerInterface p) {
        super(p);
        hasSpecialBuild = true;
    }

    /**Builds twice on the same BoardCell
     * @param row BoardCell row
     * @param col BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */

    @Override
    public synchronized boolean build(int row, int col, @NotNull Worker worker) {
        if (enableSpecialBuild) {
            if (availableCellsToBuild(worker).contains(this.getBoard().getGrid()[row][col])) {
                BoardCell b = this.getBoard().getGrid()[row][col];
                b.setLevel((b.getLevel() + 2));
                return true;
            } else {
                return false;
            }
        }
        return player.build(row, col, worker);
    }

    /**
     * List of available cells where the worker can build twice around him
     * @param worker number of the worker
     * @return recursion for the double building
     */
    @Override
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker) {
        if(enableSpecialBuild) {
            List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
            adj.removeIf((n) -> n.getWorker() != null);
            adj.removeIf(BoardCell::getDome);
            adj.removeIf((n) -> n.getLevel() == 2 || n.getLevel() == 3);
            return adj;
        }
        return player.availableCellsToBuild(worker);
    }


}