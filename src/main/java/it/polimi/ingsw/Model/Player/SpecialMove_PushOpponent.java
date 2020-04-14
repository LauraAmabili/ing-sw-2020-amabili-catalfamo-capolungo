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

    public boolean move(int row1, int col1, Worker myWorker, int row2, int col2){
        if(availableCellsToMove(myWorker, true).contains(myWorker.getBoard().getGrid()[row1][col1])) {
            Worker opponentWorker = myWorker.getBoard().getGrid()[row1][col1].getWorker();
            if(availableCellsToBePushed(opponentWorker).contains(myWorker.getBoard().getGrid()[row2][col2])){
                BoardCell myWorkerCell=worker.getCurCell();
                BoardCell opponentBoardCell=worker.getBoard().getGrid()[row][col];
            };
        }





            return false;
    }


    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect) {
        if (specialEffect) {
            List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
            adj.removeIf((n) -> n.getWorker().getPlayerWorker().equals(worker.getPlayerWorker()));
            adj.removeIf(BoardCell::getDome);
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
            return adj;
        }
        return null;
    }

    public List<BoardCell> availableCellsToBePushed(@NotNull Worker worker) {
        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf(BoardCell::getDome);
        adj.removeIf((n) -> n.getWorker()!=null);
    }

}