package it.polimi.ingsw.Model.Player;


/*DONE
Apollo

Your Worker may move into an opponent Worker’s space by forcing their Worker to the space yours just vacated.

*/

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialMove_SwapWorkers extends PlayerDecorator {

    protected PlayerInterface player;

    // constructor
    public SpecialMove_SwapWorkers(PlayerInterface player) {
        super(player);
    }


    @Override
    public boolean move(int row, int col, @NotNull Worker worker) {
        if (availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
				if(worker.getBoard().getGrid()[row][col].getWorker()==null){
					worker.getCurCell().setWorker(null);
					worker.setOldCell(worker.getCurCell());
					worker.setCurCell(worker.getBoard().getGrid()[row][col]);
					worker.getCurCell().setWorker(worker);
					return true;
				}
				if(worker.getBoard().getGrid()[row][col].getWorker()!=null){
					BoardCell workerBoardCell=worker.getCurCell();
					BoardCell opponentBoardCell=worker.getBoard().getGrid()[row][col];
					Worker opponentWorker=opponentBoardCell.getWorker();

					worker.setOldCell(workerBoardCell);
					worker.setCurCell(opponentBoardCell);
					opponentWorker.setOldCell(opponentBoardCell);
					opponentWorker.setCurCell(workerBoardCell);
					return true;

				}



        }

        return false;
    }


    /*

    Add cells such that:
        - adjacent cell
        - opponent's worker on them

    Remove cells such that:
        - there is a dome
        - the level is greater than 1


     */
    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        List<BoardCell> all = worker.getBoard().allCells();
        all.removeIf(BoardCell::getDome);
        all.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        all.removeIf((n) -> n.getWorker().getPlayerWorker().equals(worker.getPlayerWorker()));
        all.removeIf((n) -> (n.getWorker()==null && !(adj.contains(n))));
        return all;

    }


}
	


