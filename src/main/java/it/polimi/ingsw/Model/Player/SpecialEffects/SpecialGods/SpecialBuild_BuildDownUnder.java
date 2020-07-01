package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialBuild_BuildDownUnder extends PlayerDecorator {

    public SpecialBuild_BuildDownUnder(PlayerInterface p) {
        super(p);
        //hasSpecialBuild = true;
    }

    /**
     * Permit to build also under the selected worker
     * @param row
     * @param col
     * @param worker
     * @return
     */
    @Override
    public synchronized boolean build(int row, int col, @NotNull Worker worker) {
        if (availableCellsToBuild(worker).contains(this.getBoard().getGrid()[row][col])) {
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

    /**
     * return the list of cell where the player can build
     * @param worker
     * @return
     */
    @Override
    public List<BoardCell> availableCellsToBuild(Worker worker) {
        List<BoardCell> b = player.availableCellsToBuild(worker);
        b.add(worker.getCurCell());
        return b;
    }


}