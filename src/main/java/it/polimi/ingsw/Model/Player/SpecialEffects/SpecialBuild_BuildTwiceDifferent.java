package it.polimi.ingsw.Model.Player.SpecialEffects;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;


public class SpecialBuild_BuildTwiceDifferent extends PlayerDecorator {

    private final static boolean hasSpecialBuild=true;


    public SpecialBuild_BuildTwiceDifferent(PlayerInterface p) {
        super(p);
    }

    /**Builds on two different BoardCells
     * @param row1 First BoardCell row
     * @param col1 First BoardCell col
     * @param worker Worker used
     * @param row2 Second BoardCell row
     * @param col2 Second BoardCell col
     * @return true <--> the method works </-->
     */
    public boolean build(int row1, int col1, @NotNull Worker worker, int row2, int col2, boolean specialEffect) {

        BoardCell b1 = this.getBoard().getGrid()[row1][col1];
        BoardCell b2 = this.getBoard().getGrid()[row2][col2];
        if (specialEffect) {
            if(availableCellsToBuild(worker).contains(b1) && availableCellsToBuild(worker).contains(b2)){
                if(!b1.equals(b2)) {
                    build(row1, col1, worker);
                    build(row2, col2, worker);
                }
                return true;
            }
            return false;
        }
        return build(row1, col1, worker);
    }
}
