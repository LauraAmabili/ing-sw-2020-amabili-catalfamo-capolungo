package it.polimi.ingsw.Decorator;

//Minotaur - push opponent's worker

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpecialMove3 extends PlayerDecorator{

    // constructor
    public SpecialMove3(PlayerInterface p){
        super(p);
    }

    public void decorate(){}

    public boolean move(int row, int col, Worker worker){
        worker.getCurCell().setWorker(null);
        worker.setOldCell(worker.getCurCell());
        worker.setCurCell(worker.getBoard().getGrid()[row][col]);
        worker.getCurCell().setWorker(worker);

        // effect activation call specialEffect()
        return false;
    }

    public void specialEffect(Worker worker, Worker opponentWorker, int row, int col)

    {
        move(row, col, opponentWorker);
    }

    public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
        return null;
    };

}