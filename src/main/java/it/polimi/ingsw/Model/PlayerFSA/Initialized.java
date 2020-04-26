package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Initialized extends PlayerFSA {

    PlayerInterface player;

    public Initialized(PlayerInterface player) {
        this.player = player;
    }

    @Override
    public void chosenCards(List<God> godName) {
        for (God god : godName) {
            player.getChosenGods().add(god);
        }
        player.setPlayerState(new Idle(player, new Initialized(player)));
    }

}
