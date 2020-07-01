package it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;


public class SpecialWin_MoveDown extends PlayerDecorator {

    public SpecialWin_MoveDown(PlayerInterface p) {
        super(p);
    }

    /**
     * Win if you go up to level 3 or down of two levels or more
     *
     * @param worker number of the worker
     * @return boolean
     */
    @Override
    public synchronized boolean checkWin(@NotNull Worker worker) {
        if (worker.getOldCell()==worker.getCurCell())
            return false;
        return
                (player.checkWin(worker) ||
                        (worker.getOldCell().getLevel() - worker.getCurCell().getLevel() >= 2)
                );

    }
}