package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;


public class SpecialMove_PushOpponent extends PlayerDecorator {

    // constructor
    public SpecialMove_PushOpponent(PlayerInterface p) {
        super(p);
    }

    /**You can move your Worker into an opponent's pushable BoardCell
     * @param row
     * @param col
     * @param worker
     * @return
     */
    public boolean move(int row, int col, @NotNull Worker worker) {

        if (availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            Worker opponentWorker = worker.getBoard().getGrid()[row][col].getWorker();
            if (opponentWorker == null) {
                worker.getCurCell().setWorker(null);
                worker.setOldCell(worker.getCurCell());
                worker.setCurCell(worker.getBoard().getGrid()[row][col]);
                worker.getCurCell().setWorker(worker);
                return true;
            } else {
                BoardCell cell = worker.getCurCell();
                BoardCell opponentCell = opponentWorker.getCurCell();
                int pushedRow = 0;
                int pushedCol = 0;

                if (cell.getRow() == opponentCell.getRow())
                    pushedRow = cell.getRow();
                if (cell.getRow() == opponentCell.getRow() + 1)
                    pushedRow = cell.getRow() - 2;
                if (cell.getRow() == opponentCell.getRow() - 1)
                    pushedRow = cell.getRow() + 2;
                if (cell.getCol() == opponentCell.getCol())
                    pushedCol = cell.getRow();
                if (cell.getCol() == opponentCell.getCol() + 1)
                    pushedCol = cell.getRow() - 2;
                if (cell.getCol() == opponentCell.getCol() - 1)
                    pushedCol = cell.getRow() + 2;

                BoardCell pushedCell = worker.getBoard().getGrid()[pushedRow][pushedCol];
                if (availableCellsToBePushed(opponentWorker).contains(pushedCell)) {
                    worker.setOldCell(cell);
                    worker.setCurCell(opponentCell);
                    opponentWorker.setOldCell(opponentCell);
                    opponentWorker.setCurCell(pushedCell);
                    return true;
                }
                return false;
            }

        }
        return false;
    }


    /**
     * returns the BoardCells such that:
     * - distance(worker)<=1
     * - there are not Workers of the same Player
     * - if there is a Worker, that Worker is Pushable
     * - there are not Domes
     * - if (moveUp), remove BoardCells such that BoardCell.level+1>Worker.level
     * - if !(moveUp), remove BoardCells such that BoardCell.level>Worker.level
     *
     * @param worker Worker used
     * @return ArrayList of BoardCells
     */
    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());

        adj.removeIf(x -> x.getWorker() != null &&
                x.getWorker().getPlayerWorker() == worker.getPlayerWorker());
        adj.removeIf(BoardCell::getDome);
        if (worker.getPlayerWorker().isMoveUp()) {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        } else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }
        BoardCell cell = worker.getCurCell();
        for (BoardCell opponentCell : adj) {
            if (opponentCell.getWorker() != null) {
                int pushedRow = 0;
                int pushedCol = 0;
                if (cell.getRow() == opponentCell.getRow())
                    pushedRow = cell.getRow();
                if (cell.getRow() == opponentCell.getRow() + 1)
                    pushedRow = cell.getRow() - 2;
                if (cell.getRow() == opponentCell.getRow() - 1)
                    pushedRow = cell.getRow() + 2;
                if (cell.getCol() == opponentCell.getCol())
                    pushedCol = cell.getRow();
                if (cell.getCol() == opponentCell.getCol() + 1)
                    pushedCol = cell.getRow() - 2;
                if (cell.getCol() == opponentCell.getCol() - 1)
                    pushedCol = cell.getRow() + 2;
                BoardCell pushedCell = worker.getBoard().getGrid()[pushedRow][pushedCol];
                if (!(availableCellsToBePushed(opponentCell.getWorker()).contains(pushedCell))) {
                    adj.remove(opponentCell);
                }

            }
        }


        return adj;
    }


    /**
     * returns the BoardCells such that:
     * - there are not Workers
     * - there are not Domes
     *
     * @param worker Worker used
     * @return ArrayList of BoardCells
     */
    public List<BoardCell> availableCellsToBePushed(@NotNull Worker worker) {
        List<BoardCell> res = worker.getBoard().adjacentCells(worker.getCurCell());
        res.removeIf(BoardCell::getDome);
        res.removeIf((n) -> n.getWorker() != null);
        return res;
    }
}
