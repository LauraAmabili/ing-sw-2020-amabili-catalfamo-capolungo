package it.polimi.ingsw.Decorator;

// Atlas - Build dome at any level

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

//Atlas
public class SpecialBuild_DomeAnyLevel extends PlayerDecorator {

	protected PlayerInterface player;

	// constructor
	public SpecialBuild_DomeAnyLevel(PlayerInterface player){
		super(player);
	}

	public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){

		List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
		adj.removeIf((n)-> n.getWorker() != null);
		adj.removeIf(BoardCell::getDome);
		return adj;

	}


	//build a dome at any level
	public boolean build(int row, int col, Worker worker) {
		if(availableCellsToBuild(worker).contains(worker.getBoard().getGrid()[row][col])) {
			worker.getBoard().getGrid()[row][col].setDome(true);
			return true;
		}
		return false;
	}

}