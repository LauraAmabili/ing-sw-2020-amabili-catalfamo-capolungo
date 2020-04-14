package it.polimi.ingsw.Model.Player;

// Atlas - Build dome at any level

import it.polimi.ingsw.Model.*;

//Atlas
public class SpecialBuild_DomeAnyLevel extends PlayerDecorator {

	protected PlayerInterface player;

	// constructor
	public SpecialBuild_DomeAnyLevel(PlayerInterface player){
		super(player);
	}


	//build a dome at any level
	public boolean build(int row, int col, Worker worker, boolean specialEffect) {
		if (specialEffect){
			if(availableCellsToBuild(worker).contains(worker.getBoard().getGrid()[row][col])) {
				worker.getBoard().getGrid()[row][col].setDome(true);
				return true;
			}

		}
		return false;
	}

}