package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.Worker;

public class SpecialBuild2 extends PlayerDecorator{

	// constructor
	public SpecialBuild2(PlayerInterface p){
		super(p);
	}

	@Override
	public void move(int row, int col, Worker worker) {

	}

	@Override
	public void build(int row, int col, Worker worker) {
	}

	@Override
	public boolean checkWin(Worker worker) {
		return false;

	}

	//@Override
	//public String decorate(){
	//	return super.decorate() + SpecialBuild2();
	//}
	
	public String SpecialBuild2() {
		return " Adding SpecialBuild2";
	}

}