package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SpecialMove_BMB extends PlayerDecorator {

    private final boolean hasSpecialMove;
    private final boolean hasTwoInputMove;

    private boolean enableSpecialMove;

    @Override
    public boolean isHasTwoInputMove() {
        return hasTwoInputMove;
    }

    @Override
    public boolean isHasSpecialMove() {
        return hasSpecialMove;
    }

    @Override
    public boolean isEnableSpecialMove() {
        return enableSpecialMove;
    }

    @Override
    public void setEnableSpecialMove(boolean enableSpecialMove) {
        this.enableSpecialMove = enableSpecialMove;
    }

    public SpecialMove_BMB(PlayerInterface player) {
        super(player);
        hasSpecialMove = true;
        hasTwoInputMove = true;
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
    public boolean move(int rowBuild, int colBuild, int row, int col, @NotNull Worker worker) {
        if (enableSpecialMove) {
            return player.build(rowBuild, colBuild, worker) && player.move(row, col, worker);
        }
        return player.move(row, col, worker);
    }

    @Override
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