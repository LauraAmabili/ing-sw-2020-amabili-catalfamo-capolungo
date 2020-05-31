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
     *
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
                worker.setOldCell(worker.getCurCell());
                worker.setCurCell(opponentWorker.getCurCell());
                opponentWorker.setOldCell(opponentWorker.getCurCell());
                opponentWorker.setCurCell(otherside(worker, opponentWorker));
                worker.getOldCell().setWorker(null);
                worker.getCurCell().setWorker(worker);
                opponentWorker.getCurCell().setWorker(opponentWorker);
                return true;
            }

        }
        return false;

    }


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
                if (otherside(worker, adj.get(i).getWorker()) == null)
                    adj.remove(i);
        }


        return adj;
    }


    public BoardCell otherside(Worker pushing, Worker pushed) {
        int rowPushingCell = pushing.getCurCell().getRow();
        int colPushingCell = pushing.getCurCell().getCol();
        int rowPushedCell = pushed.getCurCell().getRow();
        int colPushedCell = pushed.getCurCell().getCol();
        int rowDestinationCell = 0;
        int colDestinationCell = 0;
        if (rowPushedCell > rowPushingCell)
            rowDestinationCell = rowPushingCell - (rowPushedCell - rowPushingCell);
        if (rowPushedCell == rowPushingCell)
            rowDestinationCell = rowPushedCell;
        if (colPushedCell < colPushingCell)
            colDestinationCell = colPushedCell + (colPushingCell - colPushedCell);
        if (colPushedCell > colPushingCell)
            colDestinationCell = colPushingCell - (colPushedCell - colPushingCell);
        if (colPushedCell == colPushingCell)
            colDestinationCell = colPushedCell;
        if (colPushedCell < colPushingCell)
            colDestinationCell = colPushedCell + (colPushingCell - colPushedCell);

        if ((rowDestinationCell < 0 || colDestinationCell < 0 || rowDestinationCell > 4 || colDestinationCell > 4))
            return null;
        BoardCell destinationCell = this.getBoard().getGrid()[rowDestinationCell][rowPushedCell];
        if (destinationCell.getDome())
            return null;
        if (destinationCell.getWorker() != null)
            return null;
        return destinationCell;

    }


}
