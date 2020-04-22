package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

public class Idle implements PlayerFSA {

    PlayerInterface player;

    public Idle(Player player) {
        this.player = player;
    }

    @Override
    public void setCard(God godName) {
    }

    @Override
    public void Move(int row, int col, Worker worker) {

    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
