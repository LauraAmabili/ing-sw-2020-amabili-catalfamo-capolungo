package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Initialized implements PlayerFSA {

    PlayerInterface player;

    public Initialized(PlayerInterface player) {
        this.player = player;
    }


    @Override
    public void addNickname(String name) {

    }

    @Override
    public void chosenCards(List<God> godName) {
        for (God god : godName) {
            player.getChosenGods().add(god);
        }
        player.setOldPlayerState(player.getInitialized());
        player.setPlayerState(player.getIdle());
    }

    @Override
    public void setCard(God godName) {

    }

    @Override
    public void placeWorker(int row, int col, Worker worker) {

    }

    @Override
    public void Move(int row, int col, Worker worker) {

    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
