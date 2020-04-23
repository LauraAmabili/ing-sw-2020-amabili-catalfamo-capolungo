package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Moving implements PlayerFSA {

    PlayerInterface player;

    public Moving(PlayerInterface player) {
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

    }

    @Override
    public void Move(int row, int col, Worker worker) {
        player.move(row, col, worker);
        player.setOldPlayerState(player.getMoving());
        player.setPlayerState(player.getBuilding());
    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
