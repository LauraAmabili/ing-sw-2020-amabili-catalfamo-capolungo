package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Idle extends PlayerFSA {

    PlayerInterface player;
    PlayerFSA oldState;

    public Idle(PlayerInterface player, PlayerFSA oldState) {
        this.player = player;
        this.oldState = oldState;
    }


    @Override
    public void Next() {
        if(oldState.equals(new AddNickname(player)) || oldState.equals(new Initialized(player))) {
            player.setPlayerState(new SetCard(player));
        } else if(oldState.equals(new SetCard(player))) {
            player.setPlayerState(new PlaceWorker(player));
        } else if(oldState.equals(new PlaceWorker(player))) {
            player.setPlayerState(new Moving(player));
        } else if(oldState.equals(new Building(player))) {
            player.setPlayerState(new Moving(player));
        }
    }
}
