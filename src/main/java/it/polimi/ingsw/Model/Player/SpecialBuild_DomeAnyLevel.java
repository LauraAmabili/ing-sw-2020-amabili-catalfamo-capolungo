package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;



public class SpecialBuild_DomeAnyLevel extends PlayerDecorator {

	protected PlayerInterface player;

	public SpecialBuild_DomeAnyLevel(PlayerInterface player){
		super(player);
	}

	/**Builds a dome on that BoardCell
	 * @param row BoardCell row
	 * @param col BoardCell col
	 * @param worker Worker used
	 * @param specialEffect true <--> the effect has to be enabled</-->
	 * @return true <--> the method works </-->
	 */

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