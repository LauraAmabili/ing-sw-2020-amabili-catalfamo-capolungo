package it.polimi.ingsw.Decorator;

// Atlas - Build dome at any level

import it.polimi.ingsw.Model.*;

import java.util.ArrayList;

//Atlas
public class SpecialBuild1 extends PlayerDecorator{

	// constructor
	public SpecialBuild1(PlayerInterface p){
		super(p);
	}

	public void decorate(){}
	
	//build a dome at any level
	public boolean build(int row, int col, Worker worker) {
		BoardCell b = worker.getBoard().getGrid()[row][col];

        b.setDome(true);
		return false;
	}

}