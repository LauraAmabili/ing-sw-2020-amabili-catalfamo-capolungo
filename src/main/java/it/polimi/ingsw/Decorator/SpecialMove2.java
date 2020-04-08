package it.polimi.ingsw.Decorator;

//Artemis - Move twice

import it.polimi.ingsw.Model.*;

import java.util.ArrayList;

public class SpecialMove2 extends PlayerDecorator{

	// constructor
	public SpecialMove2(PlayerInterface p){
		super(p);
	}

	public void decorate(){}

	public void move(int row, int col, Worker worker){
		worker.getCurCell().setWorker(null);
		worker.setOldCell(worker.getCurCell());
		worker.setCurCell(worker.getBoard().getGrid()[row][col]);
		worker.getCurCell().setWorker(worker);

		// effect activation call specialEffect()

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

}