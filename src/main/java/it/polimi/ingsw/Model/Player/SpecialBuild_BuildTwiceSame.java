package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/*
Hephaestus

Your Worker may build one additional block (not dome) on top of your first block.

 */


public class SpecialBuild_BuildTwiceSame extends PlayerDecorator {

    public SpecialBuild_BuildTwiceSame(PlayerInterface p) {
        super(p);
    }

    public boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect) {
        if (specialEffect) {
            if (availableCellsToBuild(worker, true).contains(worker.getBoard().getGrid()[row][col])) {
                BoardCell b = worker.getBoard().getGrid()[row][col];
                b.setLevel((b.getLevel() + 2));
                return true;
            }
        }
        return false;
    }


    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect) {
        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        adj.removeIf((n) -> n.getLevel() == 2);
        return adj;

    }


}