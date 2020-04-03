package it.polimi.ingsw;

public class SpecialMove2 extends PlayerDecorator{

	// constructor
	public SpecialMove2(PlayerInterface p){
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
		return super.decorate() + SpecialMove2();
	}
	*/
	public String SpecialMove2() {
		return " Adding SpecialMove2";
	}

}