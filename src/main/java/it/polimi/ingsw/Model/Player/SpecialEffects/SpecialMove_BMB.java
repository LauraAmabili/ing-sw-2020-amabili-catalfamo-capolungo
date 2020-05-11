package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_BMB extends PlayerDecorator {

    private boolean hasSpecialMove = true;


    @Override
    public boolean isEnableSpecialMove() {
        return enableSpecialMove;
    }

    @Override
    public void setEnableSpecialMove(boolean enableSpecialMove) {
        this.enableSpecialMove = enableSpecialMove;
    }

    private boolean enableSpecialMove;

    public SpecialMove_BMB(PlayerInterface player) {
        super(player);
        hasSpecialMove = true;
    }

    /**Build, move on the same level, build
     * @param row First BoardCell row
     * @param col First BoardCell col
     * @param worker Worker used
     * @param rowBuild Second BoardCell row
     * @param colBuild Second BoardCell col
     * @return true <--> the method works </-->
     */
    @Override
    public boolean move(int row, int col, @NotNull Worker worker, int rowBuild, int colBuild) {
        if (enableSpecialMove){
            build(rowBuild, colBuild, worker);
        }
        return player.move(row, col, worker);
    }

    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        if (enableSpecialMove) {
            List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
            adj.removeIf((n) -> n.getWorker() != null);
            adj.removeIf(BoardCell::getDome);
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
            return adj;
        }
        return player.availableCellsToMove(worker);
    }

}