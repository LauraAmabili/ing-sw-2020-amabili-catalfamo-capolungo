package it.polimi.ingsw.Decorator;

//Demeter - build twice

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;


public class SpecialBuild_BuildTwiceDifferent extends PlayerDecorator {

    int usedRow;
    int usedCol;
    Worker usedWorker;

    // constructor
    public SpecialBuild_BuildTwiceDifferent(PlayerInterface p) {
        super(p);
    }

    public void decorate() {
    }

    //ask the user if they want to perform a second build
    //if yes, set wantSecondBuild to true
    //and assign values to row2, col2

    public boolean build(int row, int col, @NotNull Worker worker) {

        BoardCell b = worker.getBoard().getGrid()[row][col];
        if (b.getLevel() == 3) {
            b.setDome(true);
        } else {
            b.setLevel((b.getLevel() + 1));
        }
        usedRow = row;
        usedCol = col;
        usedWorker = worker;
        return false;
    }

    public boolean buildTwice(int row, int col) {
        if (row != usedRow || col != usedCol) {

            BoardCell b = usedWorker.getBoard().getGrid()[row][col];

            if (b.getLevel() == 3) {
                b.setDome(true);
            } else {
                b.setLevel((b.getLevel() + 1));
            }
        }


        return false;
    }
}