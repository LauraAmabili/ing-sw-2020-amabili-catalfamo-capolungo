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
     * @param worker
     * @return
     */
    public boolean checkWin(@NotNull Worker worker) {

        return
                (this.checkWin(worker) ||
                        (worker.getOldCell().getLevel() - worker.getCurCell().getLevel() >= 2)
                );

    }
}