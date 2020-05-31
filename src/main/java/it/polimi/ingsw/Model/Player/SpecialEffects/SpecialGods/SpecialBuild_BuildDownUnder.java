package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialBuild_BuildDownUnder extends PlayerDecorator {

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

    public SpecialBuild_BuildDownUnder(PlayerInterface p) {
        super(p);
        hasSpecialBuild = true;
    }

    /**
     * Builds twice on the same BoardCell
     *
     * @param row    BoardCell row
     * @param col    BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */

    @Override
    public boolean build(int row, int col, @NotNull Worker worker) {
        if (worker.getCurCell().getRow()==row && worker.getCurCell().getRow()==col){
            if (enableSpecialBuild){
                for (int i = 0; i < availableCellsToBuild(worker,true).size(); i++) {
                    if (availableCellsToBuild(worker,true).get(i).getRow()==row &&
                            availableCellsToBuild(worker,true).get(i).getCol()==col){
                        worker.getCurCell().setLevel(worker.getCurCell().getLevel()+1);
                        return true;
                    }


                }

            }
            return false;

        }
        else {
            player.build(row,col,worker);
        }
        return false;
    }

    @Override
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect) {
        if (enableSpecialBuild) {
            List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
            adj.removeIf((n) -> n.getWorker() != null);
            adj.add(worker.getCurCell());
            adj.removeIf(BoardCell::getDome);
            adj.removeIf((n) -> n.getLevel() == 2);
            return adj;
        }
        return player.availableCellsToBuild(worker, false);
    }


}