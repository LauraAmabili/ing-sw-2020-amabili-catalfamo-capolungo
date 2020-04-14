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
        List<BoardCell> adj2 = worker.getBoard().adjacentCells(worker.getCurCell());
        for (BoardCell x : adj2) {
            List<BoardCell> temp = worker.getBoard().adjacentCells(x);
            for (BoardCell y : temp) {
                if (!(adj2.contains(y))) {
                    adj2.add(y);
                }

            }
        }
        adj2.removeIf((n) -> n.getWorker() != null);
        adj2.removeIf(BoardCell::getDome);
        adj2.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        return adj2;
    }


}