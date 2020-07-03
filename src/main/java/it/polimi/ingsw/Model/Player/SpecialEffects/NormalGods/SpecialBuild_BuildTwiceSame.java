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

    /**
     * Builds twice on the same BoardCell
     *
     * @param row    BoardCell row
     * @param col    BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */

    @Override
    public synchronized boolean build(int row, int col, @NotNull Worker worker) {
        BoardCell b = this.getBoard().getGrid()[row][col];

        if (enableSpecialBuild) {
            if (availableCellsToBuild(worker).contains(b)){
                if (b.getLevel()<=1){
                    b.setLevel((b.getLevel() + 2));
                    return true;
                }

                else if (b.getLevel()==2){
                    b.setLevel((b.getLevel() + 1));
                    return true;
                }

                else if (b.getLevel()==3){
                    b.setDome(true);
                    return true;
                }

            }


            return false;
        }
        else {
            return player.build(row, col, worker);
        }
    }


}