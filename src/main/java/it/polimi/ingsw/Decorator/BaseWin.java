package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

public class BaseWin extends PlayerDecorator{

    protected PlayerInterface player;

    public BaseWin(PlayerInterface player) {
        super(player);
    }

    public boolean checkWin(@NotNull Worker worker) {
        return ((worker.getOldCell().getLevel() < worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3);
    }
}
