package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class SetCard extends PlayerFSA{

    PlayerInterface player;

    public SetCard(PlayerInterface player) {
        this.player = player;
    }

    @Override
    public void setCard(God godName) {
        player.setActiveCard(godName);
        player.setPlayerState(new Idle(player, new SetCard(player)));
    }

}
