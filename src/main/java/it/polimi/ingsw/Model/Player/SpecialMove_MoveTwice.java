package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;


/*Artemis

Your Worker may move one additional time, but not back to its initial space.

*/


public class SpecialMove_MoveTwice extends PlayerDecorator {

    // constructor
    public SpecialMove_MoveTwice(PlayerInterface p) {
        super(p);
    }

    // return an Arraylist of the boardcell where the worker can move
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        for (BoardCell x : adj) {
            List<BoardCell> temp = worker.getBoard().adjacentCells(x);
            for (BoardCell y : temp) {
                if (!(adj.contains(y))) {
                    adj.add(y);
                }

            }
        }
        adj.removeIf((n) -> n.getWorker() != null);
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