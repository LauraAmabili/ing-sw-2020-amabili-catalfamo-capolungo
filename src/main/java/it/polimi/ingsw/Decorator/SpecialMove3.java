package it.polimi.ingsw.Decorator;

//Minotaur - push opponent's worker
//TODO: it does not work

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialMove3 extends PlayerDecorator {

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


    //worker can move everywhere even if there is a worker there
    //TODO: switched worker must update his position
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker){

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf(BoardCell::getDome);
        return adj;

    };

}