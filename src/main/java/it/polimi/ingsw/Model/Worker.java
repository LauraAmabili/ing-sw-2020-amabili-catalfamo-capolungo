package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.Serializable;

public class Worker implements Serializable {

    private int idWorker;
    private PlayerInterface playerWorker;
    private BoardCell oldCell;
    private BoardCell curCell;

    public Worker(int idWorker) {
        this.idWorker = idWorker;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public PlayerInterface getPlayerWorker() {
        return playerWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public void setPlayerWorker(PlayerInterface playerWorker) {
        this.playerWorker = playerWorker;
    }

    public BoardCell getOldCell() {
        return oldCell;
    }

    public void setOldCell(BoardCell oldCell) {
        this.oldCell = oldCell;
    }

    public BoardCell getCurCell() {
        return curCell;
    }

    public void setCurCell(BoardCell curCell) {
        this.curCell = curCell;
    }

}
