package it.polimi.ingsw.Model.Player.SpecialEffects;
import it.polimi.ingsw.Model.*;
import org.jetbrains.annotations.NotNull;


public class SpecialWin_MoveDown extends PlayerDecorator {

    public SpecialWin_MoveDown(PlayerInterface p){
        super(p);
    }

    /**Win if you go up to level 3 or down of two levels or more
     * @param worker
     * @return
     */
    public boolean checkWin(@NotNull Worker worker) {

        return (
            ((worker.getOldCell().getLevel() < worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3) ||
                (worker.getOldCell().getLevel() - worker.getCurCell().getLevel() >= 2)
        );

    }
}