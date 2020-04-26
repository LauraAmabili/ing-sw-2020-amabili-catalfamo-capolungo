package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;
import java.util.Objects;

public class AddNickname extends PlayerFSA {

    PlayerInterface player;

    public AddNickname(PlayerInterface player) {
        this.player = player;
    }

    @Override
    public void addNickname(String name) {
        player.setNickname(name);
        player.setPlayerState(new Idle(player, new AddNickname(player)));
    }

    public PlayerInterface getPlayer() {
        return player;
    }

}
