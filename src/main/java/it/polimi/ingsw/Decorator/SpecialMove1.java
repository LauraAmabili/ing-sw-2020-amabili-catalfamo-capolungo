package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.Worker;

public class SpecialMove1 extends PlayerDecorator{

	// constructor
	public SpecialMove1(PlayerInterface p){
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
	 */
	/*
	public void decorate(){
		super.decorate();
		SpecialMove1(row, col, worker);
	}*/
	
	public void SpecialMove1(int row, int col, Worker worker) {
		/*

		public void move(int row, int col, Worker worker);
		fai due mosse
		 */
	}

}