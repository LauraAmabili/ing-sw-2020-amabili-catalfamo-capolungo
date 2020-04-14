package it.polimi.ingsw.Model.Player;

//Artemis - Move twice

import it.polimi.ingsw.Model.*;

public class SpecialMove2 extends PlayerDecorator {

	// constructor
	public SpecialMove2(PlayerInterface p){
		super(p);
	}

	public boolean move(int row, int col, Worker worker){
		worker.getCurCell().setWorker(null);
		worker.setOldCell(worker.getCurCell());
		worker.setCurCell(worker.getBoard().getGrid()[row][col]);
		worker.getCurCell().setWorker(worker);

		// effect activation call specialEffect()
		return false;
	}

	public void specialEffect(BoardCell forbiddenCell, int row, int col, Worker worker)

	{
		if (forbiddenCell.getRow()!=row || forbiddenCell.getCol()!=col){
			worker.getCurCell().setWorker(null);
			worker.setOldCell(worker.getCurCell());
			worker.setCurCell(worker.getBoard().getGrid()[row][col]);
			worker.getCurCell().setWorker(worker);
		}

	}

	/*
	public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
		BoardCell b = worker.getCurCell();
        	int r_temp = b.getRow() - 2;
        	if(r_temp < 0) { r_temp = 0;}
        	int c_temp = b.getCol() - 2;
        	if(c_temp < 0) { c_temp = 0;}
		List<BoardCell> adj = new ArrayList<>();
        	for (int i = r_temp; i <= b.getRow() + 2 && i < SIZE; i++) {
            		for (int j = c_temp; j <= b.getCol() + 2 && j < SIZE; j++) {
                		if (!(i == b.getRow() && j == b.getCol())) {
                    			adj.add(grid[i][j]);
                			}
            			}
			}
		adj.removeIf((n)-> n.getWorker() != null);
		adj.removeIf(BoardCell::getDome);
		return adj;

	}

	 */

}