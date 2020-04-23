package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class AddNickname implements PlayerFSA {

    PlayerInterface player;

    public AddNickname(PlayerInterface player) {
        this.player = player;
    }

    @Override
    public void addNickname(String name) {
        player.setNickname(name);
        player.setOldPlayerState(player.getAddNickname());
        player.setPlayerState(player.getIdle());
    }

    @Override
    public void chosenCards(List<God> godName) {

    }

    @Override
    public void setCard(God GodName) {

    }

    @Override
    public void Move(int row, int col, Worker worker) {

    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
