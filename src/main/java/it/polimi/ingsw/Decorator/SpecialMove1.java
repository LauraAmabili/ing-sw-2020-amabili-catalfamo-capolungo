package it.polimi.ingsw.Decorator;

//Apollo - swap workers

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialMove1 extends PlayerDecorator {

	protected PlayerInterface player;

	// constructor
	public SpecialMove1(PlayerInterface player){
		super(player);
	}

	@Override
	public boolean move(int row, int col, @NotNull Worker worker) {
		if(availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
			if (worker.getBoard().getGrid()[row][col].getWorker() != null) {
				worker.getCurCell().setWorker(worker.getBoard().getGrid()[row][col].getWorker());
				worker.getBoard().getGrid()[row][col].getWorker().setCurCell(worker.getCurCell());
			} else {
				worker.getCurCell().setWorker(null);
			}
			worker.setOldCell(worker.getCurCell());
			worker.setCurCell(worker.getBoard().getGrid()[row][col]);
			worker.getCurCell().setWorker(worker);
			return true;
		}
		return false;
	}


	//worker can move in every cells he wants even if there are other workers
	@Override
	public List<BoardCell> availableCellsToMove(@NotNull Worker worker){

		List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
		adj.removeIf(BoardCell::getDome);
		adj.removeIf((n)-> (n.getLevel() > worker.getCurCell().getLevel()+1));
		return adj;

	}

}