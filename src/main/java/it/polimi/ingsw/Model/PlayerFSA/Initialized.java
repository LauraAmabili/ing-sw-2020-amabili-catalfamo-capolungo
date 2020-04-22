package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

public class Initialized implements PlayerFSA {

    PlayerInterface player;

    public Initialized(Player player) {
        this.player = player;
    }

    @Override
    public void setCard(God godName) {
        player.setActiveCard(godName);
    }

    @Override
    public void Move(int row, int col, Worker worker) {

    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
