package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

/*

 Atlas

 Your Worker may build a dome at any level.

 */

public class SpecialBuild_DomeAnyLevel extends PlayerDecorator {

	protected PlayerInterface player;

	public SpecialBuild_DomeAnyLevel(PlayerInterface player){
		super(player);
	}

	public boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect) {
		if (specialEffect){
			if(availableCellsToBuild(worker).contains(worker.getBoard().getGrid()[row][col])) {
				worker.getBoard().getGrid()[row][col].setDome(true);
				return true;
			}

		}
		return false;
	}

}