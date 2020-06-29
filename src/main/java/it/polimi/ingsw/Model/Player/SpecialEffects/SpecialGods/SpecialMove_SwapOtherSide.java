package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_SwapOtherSide extends PlayerDecorator {

    protected PlayerInterface player;

    // constructor
    public SpecialMove_SwapOtherSide(PlayerInterface p) {
        super(p);
    }

    /**
     * You can move your Worker into an opponent's pushable BoardCell
     * @param row
     * @param col
     * @param worker
     * @return
     */
    @Override
    public boolean move(int row, int col, @NotNull Worker worker) {
        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
            Worker opponentWorker = this.getBoard().getGrid()[row][col].getWorker();
            if (opponentWorker == null) {
                worker.getCurCell().setWorker(null);
                worker.setOldCell(worker.getCurCell());
                worker.setCurCell(this.getBoard().getGrid()[row][col]);
                worker.getCurCell().setWorker(worker);
                return true;
            } else {
                BoardCell otherCell = otherSide(worker, opponentWorker);
                worker.setOldCell(worker.getCurCell());
                worker.setCurCell(opponentWorker.getCurCell());
                opponentWorker.setOldCell(opponentWorker.getCurCell());
                opponentWorker.setCurCell(otherCell);
                worker.getOldCell().setWorker(null);
                worker.getCurCell().setWorker(worker);
                opponentWorker.getCurCell().setWorker(opponentWorker);
                return true;
            }

        }
        return false;

    }

    /**
     * Check if the worker can be pushed
     * @param worker
     * @return
     */

    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
        //adj.removeIf(x -> x.getWorker().getPlayerWorker().getNickname().equals(worker.getPlayerWorker().getNickname()));
        adj.removeIf(BoardCell::getDome);
        if (worker.getPlayerWorker().isMoveUp()) {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        } else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }

        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).getWorker() != null)
                if (otherSide(worker, adj.get(i).getWorker()) == null)
                    adj.remove(i);
        }


        return adj;
    }


    /**
     *
     * @param pushing worker that pushes
     * @param pushed worker pushed
     * @return boardCell where the pushed is moved
     */
    public BoardCell otherSide(Worker pushing, Worker pushed) {
        int rowPushingCell = pushing.getCurCell().getRow();
        int colPushingCell = pushing.getCurCell().getCol();
        int rowPushedCell = pushed.getCurCell().getRow();
        int colPushedCell = pushed.getCurCell().getCol();
        int rowDestinationCell = 0;
        int colDestinationCell = 0;
        /*if (rowPushedCell > rowPushingCell)
            rowDestinationCell = rowPushingCell + (rowPushedCell - rowPushingCell);
        if (rowPushedCell == rowPushingCell)
            rowDestinationCell = rowPushedCell;
        if (rowPushedCell < rowPushingCell)
            rowDestinationCell = rowPushingCell - (rowPushedCell - rowPushingCell);
        if (colPushedCell > colPushingCell)
            colDestinationCell = colPushingCell + (colPushedCell - colPushingCell);
        if (colPushedCell == colPushingCell)
            colDestinationCell = colPushedCell;
        if (colPushedCell < colPushingCell)
            colDestinationCell = colPushingCell - (colPushingCell - colPushedCell);*/

        if (rowPushedCell == rowPushingCell)
            rowDestinationCell = rowPushedCell;
        if (rowPushedCell > rowPushingCell)
            rowDestinationCell = rowPushingCell-1;
        if (rowPushedCell < rowPushingCell)
            rowDestinationCell = rowPushingCell+1;
        if (colPushedCell == colPushingCell)
            colDestinationCell = colPushedCell;
        if (colPushedCell > colPushingCell)
            colDestinationCell = colPushingCell-1;
        if (colPushedCell < colPushingCell)
            colDestinationCell = colPushingCell+1;

        if ((rowDestinationCell < 0 || colDestinationCell < 0 || rowDestinationCell > 4 || colDestinationCell > 4))
            return null;
        if (this.getBoard().getGrid()[rowDestinationCell][colDestinationCell].getDome())
            return null;
        if (this.getBoard().getGrid()[rowDestinationCell][colDestinationCell].getWorker() != null)
            return null;
        return this.getBoard().getGrid()[rowDestinationCell][colDestinationCell];

    }


}
