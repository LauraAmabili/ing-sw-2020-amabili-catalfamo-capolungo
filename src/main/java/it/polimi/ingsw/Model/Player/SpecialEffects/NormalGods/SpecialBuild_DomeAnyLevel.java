package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;



public class SpecialBuild_DomeAnyLevel extends PlayerDecorator {

    private final boolean hasSpecialBuild;

    @Override
    public boolean isHasSpecialBuild() {
        return hasSpecialBuild;
    }

    @Override
    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    @Override
    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }

    private boolean enableSpecialBuild;

    public SpecialBuild_DomeAnyLevel(PlayerInterface player){
        super(player);
        hasSpecialBuild = true;
    }

    /**Builds a dome on that BoardCell
     * @param row BoardCell row
     * @param col BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */
    @Override
    public boolean build(int row, int col, @NotNull Worker worker) {
        if (enableSpecialBuild) {
            if(availableCellsToBuild(worker).contains(this.getBoard().getGrid()[row][col])) {
                this.getBoard().getGrid()[row][col].setDome(true);
                return true;
            }
            return false;
        }
        return player.build(row, col, worker);
    }
}