package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
     * @return true <--> the method works
     */
    @Override
    public synchronized boolean move(int rowBuild, int colBuild, int row, int col, @NotNull Worker worker) {
        BoardCell cell1 = getBoard().getGrid()[rowBuild][colBuild];
        BoardCell cell2 = getBoard().getGrid()[row][col];

        if (cell2.getLevel()>cell1.getLevel())
            return false;

        if (!isMoveUp()){
            if (cell1.getLevel()>worker.getCurCell().getLevel())
                return false;
            if (cell2.getLevel()>worker.getCurCell().getLevel())
                return false;
        }
        if (enableSpecialMove) {
            if (availableCellsToBuild(worker).contains(cell1) && player.availableCellsToMove(worker).contains(cell2)) {
                player.build(rowBuild, colBuild, worker);
            }
            else {
                if (availableCellsToBuild(worker).size() > 0)
                    return false;
            }
        }

        return player.move(row, col, worker);
    }

    /**
     * After building we need to recalculate where the worker can move
     * @param worker number of the worker
     * @return recursion
     */
    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        if (enableSpecialMove) {
            if(player.availableCellsToBuild(worker).size() <= 1) {
                return new ArrayList<>();
            } else {
                List<BoardCell> cells = player.availableCellsToMove(worker);
                cells.removeIf(x -> x.getLevel() > worker.getCurCell().getLevel());
                return cells;
            }
        }
        return player.availableCellsToMove(worker);
    }

}