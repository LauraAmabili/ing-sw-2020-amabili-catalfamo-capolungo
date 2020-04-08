package it.polimi.ingsw.Decorator;


import it.polimi.ingsw.Model.*;

public class SpecialWin extends PlayerDecorator{

	// constructor
	public SpecialWin(PlayerInterface p){
		super(p);
	}

	public void decorate(){}

	public boolean checkWin(Worker worker) {
		return false;
	}

}