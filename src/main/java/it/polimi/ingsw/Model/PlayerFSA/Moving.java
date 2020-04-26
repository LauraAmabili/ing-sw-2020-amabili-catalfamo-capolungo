package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Moving extends PlayerFSA {

    PlayerInterface player;

    public Moving(PlayerInterface player) {
        this.player = player;
    }

    @Override
    public void Move(int row, int col, Worker worker) {
        if(player.move(row, col, worker)) {
            player.setPlayerState(new Building(player));
        } else {
            //notifyErrorMoving();
            //TODO: Send error
        }
    }

}
