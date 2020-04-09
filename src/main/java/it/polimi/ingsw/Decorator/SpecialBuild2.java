package it.polimi.ingsw.Decorator;

//Demeter - build twice

import it.polimi.ingsw.Model.*;

import java.util.ArrayList;


public class SpecialBuild2 extends PlayerDecorator{

	boolean wantSecondBuild;
	int row2;
	int col2;
	
	// constructor
	public SpecialBuild2(PlayerInterface p){
		super(p);
	}

	public void decorate(){}
	
	//ask the user if they want to perform a second build
	//if yes, set wantSecondBuild to true 
	//and assign values to row2, col2
	
	public boolean build(int row, int col, Worker worker) {
		
		 BoardCell b = worker.getBoard().getGrid()[row][col];
	        if(b.getLevel() == 3) {
	            b.setDome(true);
	        } else {
	            b.setLevel((b.getLevel() + 1));
	        }
	        
	        if(wantSecondBuild) {
	        	BoardCell b_2 = worker.getBoard().getGrid()[row2][col2];
	        	if(b_2 != b) {
	        		if(b_2.getLevel() == 3) {
	    	            b_2.setDome(true);
	    	        } else {
	    	            b_2.setLevel((b_2.getLevel() + 1));
	    	        }
	        	}
	        }
		return false;
	}

}