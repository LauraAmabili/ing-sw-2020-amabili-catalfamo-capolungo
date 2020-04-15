package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;

/*

Minotaur

Your Worker may move into an opponent Worker’s space, if their Worker can be
forced one space straight backwards to an unoccupied space at any level.

*/

public class SpecialMove_PushOpponent extends PlayerDecorator {

    // constructor
    public SpecialMove_PushOpponent(PlayerInterface p){
        super(p);
    }

    public boolean move(int row, int col, @NotNull Worker worker){
        if(availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            Worker opponentWorker = worker.getBoard().getGrid()[row][col].getWorker();
            if (opponentWorker == null){
                worker.getCurCell().setWorker(null);
                worker.setOldCell(worker.getCurCell());
                worker.setCurCell(worker.getBoard().getGrid()[row][col]);
                worker.getCurCell().setWorker(worker);
                return true;
            }
            else {
                BoardCell cell = worker.getCurCell();
                BoardCell opponentCell = opponentWorker.getCurCell();
                int pushedRow = 0;
                int pushedCol = 0;

                if (cell.getRow()==opponentCell.getRow())
                    pushedRow=cell.getRow();
                if (cell.getRow()==opponentCell.getRow()+1)
                    pushedRow=cell.getRow()-2;
                if (cell.getRow()==opponentCell.getRow()-1)
                    pushedRow=cell.getRow()+2;
                if (cell.getCol()==opponentCell.getCol())
                    pushedCol=cell.getRow();
                if (cell.getCol()==opponentCell.getCol()+1)
                    pushedCol=cell.getRow()-2;
                if (cell.getCol()==opponentCell.getCol()-1)
                    pushedCol=cell.getRow()+2;

                BoardCell pushedCell = worker.getBoard().getGrid()[pushedRow][pushedCol];
                if (availableCellsToBePushed(opponentWorker).contains(pushedCell)){
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


    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect) {
        if (specialEffect) {
            List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
            adj.removeIf((n) -> n.getWorker().getPlayerWorker().equals(worker.getPlayerWorker()));
            adj.removeIf(BoardCell::getDome);
            if (worker.getPlayerWorker().isMoveUp()){
                adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
            }
            else {
                adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
            }
            return adj;
        }
        return null;
    }

    public List<BoardCell> availableCellsToBePushed(@NotNull Worker worker) {
        List<BoardCell> res = worker.getBoard().adjacentCells(worker.getCurCell());
        res.removeIf(BoardCell::getDome);
        res.removeIf((n) -> n.getWorker()!=null);
        return res;
    }

}