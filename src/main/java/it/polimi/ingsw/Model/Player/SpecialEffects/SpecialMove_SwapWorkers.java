package it.polimi.ingsw.Model.Player.SpecialEffects;




import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class SpecialMove_SwapWorkers extends PlayerDecorator {

    protected PlayerInterface player;

    public SpecialMove_SwapWorkers(PlayerInterface player) {
        super(player);
    }


    /**You can swap your Worker with an opponent's Worker
     * @param row
     * @param col
     * @param worker
     * @return
     */
    @Override
    public boolean move(int row, int col, @NotNull Worker worker) {
        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
				if(this.getBoard().getGrid()[row][col].getWorker()==null){
					worker.getCurCell().setWorker(null);
					worker.setOldCell(worker.getCurCell());
					worker.setCurCell(this.getBoard().getGrid()[row][col]);
					worker.getCurCell().setWorker(worker);
					return true;
				}
				if(this.getBoard().getGrid()[row][col].getWorker()!=null){
					BoardCell workerBoardCell=worker.getCurCell();
					BoardCell opponentBoardCell=this.getBoard().getGrid()[row][col];
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


    /**
     *  Add cells such that:
     *         - adjacent cell
     *         - opponent's worker on them
     *
     *     Remove cells such that:
     *         - there is a dome
     *         - the level is greater than 1
     * @param worker
     * @return
     */


    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());

        adj.removeIf(BoardCell::getDome);
        if (worker.getPlayerWorker().isMoveUp()){
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        }
        else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }
        return adj;

    }


}
	

