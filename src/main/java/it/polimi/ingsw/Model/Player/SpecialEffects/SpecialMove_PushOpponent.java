package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_PushOpponent extends PlayerDecorator {

    protected PlayerInterface player;

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

        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
            Worker opponentWorker = this.getBoard().getGrid()[row][col].getWorker();
            if (opponentWorker == null) {
                worker.getCurCell().setWorker(null);
                worker.setOldCell(worker.getCurCell());
                worker.setCurCell(this.getBoard().getGrid()[row][col]);
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
                    pushedCol = cell.getCol();
                if (cell.getCol() == opponentCell.getCol() + 1)
                    pushedCol = cell.getCol() - 2;
                if (cell.getCol() == opponentCell.getCol() - 1)
                    pushedCol = cell.getCol() + 2;

                BoardCell pushedCell = this.getBoard().getGrid()[pushedRow][pushedCol];
                if (availableCellsToBePushed(opponentWorker).contains(pushedCell)) {
                    worker.setOldCell(cell);
                    worker.setCurCell(opponentCell);
                    worker.getOldCell().setWorker(null);
                    worker.getCurCell().setWorker(worker);
                    opponentWorker.setOldCell(opponentCell);
                    opponentWorker.setCurCell(pushedCell);
                    opponentWorker.getCurCell().setWorker(opponentWorker);
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

        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());

        adj.removeIf(x -> x.getWorker() != null &&
            x.getWorker().getPlayerWorker() == worker.getPlayerWorker());
        adj.removeIf(BoardCell::getDome);
        if (worker.getPlayerWorker().isMoveUp()) {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        } else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }
        BoardCell cell = worker.getCurCell();
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).getWorker() != null) {
                int pushedRow = 0;
                int pushedCol = 0;
                if (cell.getRow() == adj.get(i).getRow())
                    pushedRow = cell.getRow();
                if (cell.getRow() == adj.get(i).getRow() + 1)
                    pushedRow = cell.getRow() - 2;
                if (cell.getRow() == adj.get(i).getRow() - 1)
                    pushedRow = cell.getRow() + 2;
                if (cell.getCol() == adj.get(i).getCol())
                    pushedCol = cell.getCol();
                if (cell.getCol() == adj.get(i).getCol() + 1)
                    pushedCol = cell.getCol() - 2;
                if (cell.getCol() == adj.get(i).getCol() - 1)
                    pushedCol = cell.getCol() + 2;
                if(!(pushedRow < 0 || pushedCol < 0 || pushedRow > 4 || pushedCol > 4)) {
                    BoardCell pushedCell = this.getBoard().getGrid()[pushedRow][pushedCol];
                    if (!(availableCellsToBePushed(adj.get(i).getWorker()).contains(pushedCell))) {
                        adj.remove(adj.get(i));
                        i--;
                    }
                } else {
                    adj.remove(adj.get(i));
                    i--;
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
        List<BoardCell> res = this.getBoard().adjacentCells(worker.getCurCell());
        res.removeIf(BoardCell::getDome);
        res.removeIf((n) -> n.getWorker() != null);
        return res;
    }
}
