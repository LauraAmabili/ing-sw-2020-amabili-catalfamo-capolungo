package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerDecorator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;


public class SpecialWin_BunchOfTowers extends PlayerDecorator {

    public SpecialWin_BunchOfTowers(PlayerInterface p) {
        super(p);
    }

    private final static int towersTowin = 5;

    /**
     * Win if there are 5 towers
     *
     * @param worker
     * @return
     */
    @Override
    public synchronized boolean checkWin(@NotNull Worker worker) {

        if (worker.getOldCell() == worker.getCurCell())
            return false;
        if (((worker.getOldCell().getLevel() <
                worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3))
            return true;
        if (countTowers()>=towersTowin)
            return true;

        return false;

    }

    /**
     * Counting the towers in the game
     * @return number of the towers
     */
    public int countTowers(){
        int counter=0;
        BoardCell[][] grid = this.getBoard().getGrid();
        for (int i = 0; i< Board.getSIZE(); i++)
            for (int j = 0; j< Board.getSIZE(); j++){
                if (grid[i][j].getDome() && grid[i][j].getLevel()==3)
                    counter++;
            }
        return counter;

    }

}