package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class SetCard implements PlayerFSA{

    PlayerInterface player;

    public SetCard(PlayerInterface player) {
        this.player = player;
    }

    @Override
    public void addNickname(String name) {

    }

    @Override
    public void chosenCards(List<God> godName) {

    }

    @Override
    public void setCard(God godName) {
        player.setActiveCard(godName);
        player.setOldPlayerState(player.getSetCard());
        player.setPlayerState(player.getIdle());
    }

    @Override
    public void Move(int row, int col, Worker worker) {

    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
