package it.polimi.ingsw.Model.Player;

/*Artemis

Your Worker may move one additional time, but not back to its initial space.

*/

import it.polimi.ingsw.Model.*;

public class SpecialMove_MoveTwice extends PlayerDecorator {

	// constructor
	public SpecialMove_MoveTwice(PlayerInterface p){
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