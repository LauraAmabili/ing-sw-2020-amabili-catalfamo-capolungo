package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

/*DONE

Pan

You also win if your Worker moves down two or more levels.

 */

public class SpecialWin extends PlayerDecorator {

	// constructor
	public SpecialWin(PlayerInterface p){
		super(p);
	}

	public boolean checkWin(@NotNull Worker worker) {
		return (((worker.getOldCell().getLevel() < worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3) ||
				(worker.getOldCell().getLevel() - worker.getCurCell().getLevel() >= 2)
				);
	}
}