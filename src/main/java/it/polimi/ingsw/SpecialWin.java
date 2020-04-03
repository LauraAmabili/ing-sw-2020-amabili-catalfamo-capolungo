package it.polimi.ingsw;

public class SpecialWin  extends PlayerDecorator{

	// constructor
	public SpecialWin(PlayerInterface p){
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
	/*
	@Override
	public String decorate(){
		return super.decorate() + SpecialWin();
	}
	*/
	public String SpecialWin() {
		return " Adding SpecialWin";
	}

}