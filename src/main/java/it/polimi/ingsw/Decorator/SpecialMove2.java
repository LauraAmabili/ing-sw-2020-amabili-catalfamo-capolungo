package it.polimi.ingsw.Decorator;

//Artemis - Move twice
//TODO: it doesn'it work

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialMove2 extends PlayerDecorator {

	// constructor
	public SpecialMove2(PlayerInterface p){
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

	public void specialEffect(BoardCell forbiddenCell, int row, int col, Worker worker)

	{
		if (forbiddenCell.getRow()!=row || forbiddenCell.getCol()!=col){
			worker.getCurCell().setWorker(null);
			worker.setOldCell(worker.getCurCell());
			worker.setCurCell(worker.getBoard().getGrid()[row][col]);
			worker.getCurCell().setWorker(worker);
		}

	}

	public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
		List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
		adj.removeIf((n)-> n.getWorker() != null);
		adj.removeIf(BoardCell::getDome);
		return adj;

	};

}