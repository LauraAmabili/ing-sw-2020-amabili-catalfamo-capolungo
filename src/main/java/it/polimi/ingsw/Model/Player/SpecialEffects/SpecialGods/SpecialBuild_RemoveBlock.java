package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialBuild_RemoveBlock extends PlayerDecorator {

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

    public SpecialBuild_RemoveBlock(PlayerInterface p) {
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

        if (enableSpecialBuild) {

            Worker unmovedWorker = findUnmovedWorker(worker);
            BoardCell cellToDestroy = this.player.getBoard().getGrid()[row2][col2];
            if (unmovedWorker!=null && canDestroy(unmovedWorker).size()>0){
                if (canDestroy(unmovedWorker).contains((cellToDestroy))){
                    cellToDestroy.setLevel(cellToDestroy.getLevel()-1);
                    return player.build(row1, col1, worker);
                }
                else {
                    return false;
                }

            }
            else {
                return player.build(row1, col1, worker);
            }


        }
        return false;

    }

    private Worker findUnmovedWorker (Worker worker){




        for (Worker x : worker.getPlayerWorker().getWorkerRef()) {
            if (!(x.equals(worker)))
                return x;

        }

        return null;
    }

    private List<BoardCell> canDestroy (Worker worker) {
        List<BoardCell> destroyable = this.getBoard().adjacentCells(worker.getCurCell());
        destroyable.removeIf(BoardCell::getDome);
        destroyable.removeIf(x -> x.getWorker() != null);
        destroyable.removeIf(x -> x.getLevel() == 0);
        return destroyable;
    }



}
