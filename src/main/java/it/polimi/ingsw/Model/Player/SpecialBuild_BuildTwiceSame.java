package it.polimi.ingsw.Model.Player;

//TODO: it doesn't work
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

//Hephaestus
public class SpecialBuild_BuildTwiceSame extends PlayerDecorator {

    int usedRow;
    int usedCol;
    Worker usedWorker;
    // constructor
    public SpecialBuild_BuildTwiceSame(PlayerInterface p){
        super(p);
    }

    public boolean build(int row, int col, @NotNull Worker worker) {
        //normal build
    	
    	BoardCell b = worker.getBoard().getGrid()[row][col];  
        if(b.getLevel() == 3) {
            b.setDome(true);
        } else {
            b.setLevel(b.getLevel() + 1);
        }
        usedRow = row;
        usedCol = col;
        usedWorker = worker;


        return false;

    }

    public boolean buildTwice() {
        BoardCell b = usedWorker.getBoard().getGrid()[usedRow][usedCol];
        if (b.getLevel() == 3) {
                b.setDome(true);
        } else {
                b.setLevel((b.getLevel() + 1));
        }
        return false;
    }


}