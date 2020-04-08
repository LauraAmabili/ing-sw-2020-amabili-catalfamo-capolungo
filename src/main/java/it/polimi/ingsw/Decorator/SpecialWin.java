package it.polimi.ingsw.Decorator;


import it.polimi.ingsw.Model.*;

public class SpecialWin extends PlayerDecorator{

	// constructor
	public SpecialWin(PlayerInterface p){
		super(p);
	}

	public void decorate(){}

	public boolean checkWin(Worker worker) {
		return (((worker.getOldCell().getLevel() < worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3) ||
				(worker.getOldCell().getLevel() - worker.getCurCell().getLevel() >= 2)
				);
	}
}