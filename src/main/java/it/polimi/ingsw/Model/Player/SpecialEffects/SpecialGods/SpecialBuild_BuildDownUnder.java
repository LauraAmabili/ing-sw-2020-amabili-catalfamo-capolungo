package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialBuild_BuildDownUnder extends PlayerDecorator {

    private final boolean hasSpecialBuild;

    /*
    private boolean enableSpecialBuild;

    @Override
    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    @Override
    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }


     */
    /**
     * List of available cells where the worker can build, here we need to considerate under himself too
     * @return recursion
     */
    /*
    @Override
    public boolean isHasSpecialBuild() {
        return hasSpecialBuild;
    }

    */
    public SpecialBuild_BuildDownUnder(PlayerInterface p) {
        super(p);
        hasSpecialBuild = true;
    }

    /*
    /**
     * Builds twice on the same BoardCell
     *
     * @param row    BoardCell row
     * @param col    BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */

    /*
    @Override
    public synchronized boolean build(int row, int col, @NotNull Worker worker) {
        if (worker.getCurCell().getRow() == row && worker.getCurCell().getCol() == col) {
                worker.getCurCell().setLevel(worker.getCurCell().getLevel() + 1);
                return true;
            }
        else {
            if (player.availableCellsToBuild(worker).contains(this.getBoard().getGrid()[row][col])) {
                BoardCell b = this.getBoard().getGrid()[row][col];
                if (b.getLevel() == 3) {
                    b.setDome(true);
                } else {
                    b.setLevel((b.getLevel() + 1));
                }
                return true;
            }
            return false;
        }
    }
    */

    @Override
    public List<BoardCell> availableCellsToBuild(Worker worker) {
        List<BoardCell> b = player.availableCellsToBuild(worker);
        b.add(worker.getCurCell());
        return b;
    }


}