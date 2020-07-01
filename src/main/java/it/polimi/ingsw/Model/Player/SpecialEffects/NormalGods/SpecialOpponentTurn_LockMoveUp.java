package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;


import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

public class SpecialOpponentTurn_LockMoveUp extends PlayerDecorator {

    protected PlayerInterface player;

    private boolean toUnlock = false;

    // constructor
    public SpecialOpponentTurn_LockMoveUp(PlayerInterface player) {
        super(player);
    }


    /**moveUp parameter is managed from this function
     * if (moveUp = false)
     *      the other players were locked
     *      unlock(player)
     * if you go higher
     *      lock(player)
     * @param row chosen row
     * @param col
     * @param worker
     * @return
     */
    @Override
    public synchronized boolean move(int row, int col, @NotNull Worker worker) {
        if (toUnlock)
            unlockMoveUp(worker);
        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(this.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            if (worker.getCurCell().getLevel() > worker.getOldCell().getLevel()) {
                lockMoveUp(worker);
                return true;
            }
            return true;
        }
        return false;
    }

    /**for each player -> player.moveUp=false
     * @param worker worker used
     */
    public void lockMoveUp(Worker worker) {
        BoardCell[][] grid = this.getBoard().getGrid();
        for (BoardCell[] boardCells : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (boardCells[j].getWorker() != null) {
                    if (boardCells[j].getWorker().getPlayerWorker() != worker.getPlayerWorker()) {
                        boardCells[j].getWorker().getPlayerWorker().setMoveUp(false);
                    }
                }
            }
        }
        toUnlock = true;
    }

    /**for each player -> player.moveUp=true
     * @param worker worker used
     */
    public void unlockMoveUp(Worker worker) {
        BoardCell[][] grid = this.getBoard().getGrid();
        for (BoardCell[] boardCells : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (boardCells[j].getWorker() != null) {
                    if (boardCells[j].getWorker().getPlayerWorker() != worker.getPlayerWorker()) {
                        boardCells[j].getWorker().getPlayerWorker().setMoveUp(true);
                    }
                }
            }
        }
        toUnlock = false;
    }


}
	


