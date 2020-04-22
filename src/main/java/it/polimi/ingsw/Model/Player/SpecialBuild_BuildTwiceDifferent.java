package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;


public class SpecialBuild_BuildTwiceDifferent extends PlayerDecorator {


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
    public boolean build(int row1, int col1, @NotNull Worker worker, int row2, int col2) {
        BoardCell b1 = this.getBoard().getGrid()[row1][col1];
        BoardCell b2 = this.getBoard().getGrid()[row2][col2];
        if (!(b1.equals(b2))) {
            if(availableCellsToBuild(worker).contains(b1) &&
                    availableCellsToBuild(worker).contains(b2)){
                if(b1.getLevel() == 3) {
                    b1.setDome(true);
                } else {
                    b1.setLevel((b1.getLevel() + 1));
                }
                if (b2.getLevel() == 3) {
                    b2.setDome(true);
                } else {
                    b2.setLevel((b2.getLevel() + 1));
                }
                return true;
            }
        }
        return false;
    }
}
