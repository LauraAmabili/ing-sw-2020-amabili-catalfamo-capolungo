package it.polimi.ingsw.Model.Player.SpecialEffects;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;



public class SpecialBuild_DomeAnyLevel extends PlayerDecorator {

    private final static boolean hasSpecialBuild=true;

    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }

    private boolean enableSpecialBuild;

    public SpecialBuild_DomeAnyLevel(PlayerInterface player){
        super(player);
    }

    /**Builds a dome on that BoardCell
     * @param row BoardCell row
     * @param col BoardCell col
     * @param worker Worker used
     * @return true <--> the method works </-->
     */

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