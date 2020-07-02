package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;


public class SpecialBuild_BuildTwiceDifferent extends PlayerDecorator {

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

    public SpecialBuild_BuildTwiceDifferent(PlayerInterface p) {
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
     * @return true <--> the method works
     */
    @Override
    public synchronized boolean build(int row1, int col1, int row2, int col2, @NotNull Worker worker) {


        BoardCell b1 = this.getBoard().getGrid()[row1][col1];
        BoardCell b2 = this.getBoard().getGrid()[row2][col2];
        if(b1.equals(b2))
            return false;
        if (!availableCellsToBuild(worker).contains(b1))
            return false;
        if (availableCellsToBuild(worker).size()==1)
            return player.build(row1, col1, worker);
        if (!availableCellsToBuild(worker).contains(b2))
            return false;
        if (enableSpecialBuild) {
                return player.build(row1, col1, worker) && player.build(row2, col2, worker);
        }
        else {
            return player.build(row1, col1, worker);
        }
    }
}
