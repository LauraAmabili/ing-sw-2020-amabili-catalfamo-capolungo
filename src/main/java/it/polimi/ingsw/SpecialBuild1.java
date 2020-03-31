package it.polimi.ingsw;

public class SpecialBuild1 extends PlayerDecorator{

	// constructor
	public SpecialBuild1(PlayerInterface p){
		super(p);
	}


	@Override
	public void move(int row, int col, Worker worker) {

	}

	@Override
	public void build(Worker worker, int row, int col) {
	}

	@Override
	public boolean checkWin(Worker worker) {
		return false;

	}

	//@Override
	//public String decorate(){
	//	return super.decorate() + SpecialBuild1();
	//}
	
	public String SpecialBuild1() {
		return " Adding SpecialBuild1";
	}

}