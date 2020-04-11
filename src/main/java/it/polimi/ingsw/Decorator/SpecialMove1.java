package it.polimi.ingsw.Decorator;

//Apollo - swap workers

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialMove1 extends PlayerDecorator {

	// constructor
	public SpecialMove1(PlayerInterface p){
		super(p);
	}

	public void decorate(){}

	public boolean move(int row, int col, @NotNull Worker worker) {
		worker.getCurCell().setWorker(null);
		worker.setOldCell(worker.getCurCell());

		// effect activation call specialEffect()


		// otherwise
		worker.setCurCell(worker.getBoard().getGrid()[row][col]);
		worker.getCurCell().setWorker(worker);
		return false;
	}

	//swap workers, workers order does not matter
	public void specialEffect(Worker workerA, Worker workerB){
		BoardCell A = workerA.getCurCell();
		BoardCell B = workerA.getCurCell();
		workerA.setOldCell(A);
		workerB.setOldCell(B);
		workerA.setCurCell(B);
		workerB.setCurCell(A);
	}




	//worker can move in every cells he wants even if there are other workers
	//TODO: the worker switched must update his position
	public List<BoardCell> availableCellsToMove(@NotNull Worker worker){

		List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
		adj.removeIf(BoardCell::getDome);
		return adj;

	};

}