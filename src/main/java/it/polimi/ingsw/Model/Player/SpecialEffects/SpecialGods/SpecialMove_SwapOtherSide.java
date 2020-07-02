package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_SwapOtherSide extends PlayerDecorator {

    private boolean hasSpecialMove;
    private boolean hasTwoInputMove;

    private boolean enableSpecialMove;


    @Override
    public boolean isHasSpecialMove() {
        return hasSpecialMove;
    }

    @Override
    public boolean isHasTwoInputMove() {
        return hasTwoInputMove;
    }

    @Override
    public boolean isEnableSpecialMove() {
        return enableSpecialMove;
    }

    @Override
    public void setEnableSpecialMove(boolean enableSpecialMove) {
        this.enableSpecialMove = enableSpecialMove;
    }

    protected PlayerInterface player;

    // constructor
    public SpecialMove_SwapOtherSide(PlayerInterface p) {
        super(p);
        this.hasSpecialMove = true;
        this.hasTwoInputMove = true;
    }

    /**
     * 1. Opponent BoardCell
     * 2. Moving worker BoardCell
     *
     * @param row1 chosen row
     * @param col1 chosen col
     * @param row2 chosen row
     * @param col2 chosen col
     * @param worker Worker to move
     * @return true if moved correctly
     */
    @Override
    public synchronized boolean move(int row1, int col1, int row2, int col2, @NotNull Worker worker) {

        BoardCell cell1 = getBoard().getGrid()[row1][col1];
        BoardCell cell2 = getBoard().getGrid()[row2][col2];
        if (!isMoveUp()){
            if (cell1.getLevel()>worker.getCurCell().getLevel())
                return false;
            if (cell2.getLevel()>worker.getCurCell().getLevel())
                return false;
        }


        if (enableSpecialMove) {
            if (opponentCells(worker).size() > 0) {
                if (opponentCells(worker).contains(this.getBoard().getGrid()[row1][col1])) {
                    change(this.getBoard().getGrid()[row1][col1],
                            otherSide(worker, this.getBoard().getGrid()[row1][col1].getWorker()));
                } else {
                    return false; //wrong coordinates
                }
            }
        }
        return this.move(row2, col2, worker);
    }


    /**
     * move a worker from a boardcell to another without any further change
     * @param startingBoardCell starting board cell
     * @param finalBoardCell final board cell
     * @return boolean
     */
    public boolean change(BoardCell startingBoardCell, BoardCell finalBoardCell) {
        startingBoardCell.getWorker().setOldCell(startingBoardCell);
        startingBoardCell.getWorker().setCurCell(finalBoardCell);
        finalBoardCell.setWorker(startingBoardCell.getWorker());
        startingBoardCell.setWorker(null);
        return true;
    }

    /**
     * return opponent pushable board cell
     * @param worker worker used
     * @return pushable board cells
     */
    public List<BoardCell> opponentCells(Worker worker) {

        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf(x -> x.getWorker() == null);
        adj.removeIf(x -> x.getWorker().getPlayerWorker() == worker.getPlayerWorker());
        adj.removeIf(x -> otherSide(worker, x.getWorker()) == null);


        return adj;
    }

    /**
     * return opposite side cell
     * @param pushing worker pushing
     * @param pushed worker pushed
     * @return board cell pushed on
     */
    public BoardCell otherSide(Worker pushing, Worker pushed) {
        if (pushing.getPlayerWorker().equals(pushed.getPlayerWorker()))
            return null;
        int rowPushingCell = pushing.getCurCell().getRow();
        int colPushingCell = pushing.getCurCell().getCol();
        int rowPushedCell = pushed.getCurCell().getRow();
        int colPushedCell = pushed.getCurCell().getCol();
        int rowDestinationCell = 0;
        int colDestinationCell = 0;


        if (rowPushedCell == rowPushingCell)
            rowDestinationCell = rowPushedCell;
        if (rowPushedCell > rowPushingCell)
            rowDestinationCell = rowPushingCell - 1;
        if (rowPushedCell < rowPushingCell)
            rowDestinationCell = rowPushingCell + 1;

        if (colPushedCell == colPushingCell)
            colDestinationCell = colPushedCell;
        if (colPushedCell > colPushingCell)
            colDestinationCell = colPushingCell - 1;
        if (colPushedCell < colPushingCell)
            colDestinationCell = colPushingCell + 1;


        if ((rowDestinationCell < 0 || colDestinationCell < 0 || rowDestinationCell > 4 || colDestinationCell > 4))
            return null;
        if (this.getBoard().getGrid()[rowDestinationCell][colDestinationCell].getDome())
            return null;
        if (this.getBoard().getGrid()[rowDestinationCell][colDestinationCell].getWorker() != null)
            return null;
        return this.getBoard().getGrid()[rowDestinationCell][colDestinationCell];

    }


}
