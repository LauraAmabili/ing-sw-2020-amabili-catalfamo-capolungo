package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class PlaceWorker implements PlayerFSA{

    PlayerInterface player;
    int workerPlaced = 0;

    public PlaceWorker(PlayerInterface player) {
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
    public void placeWorker(int row, int col, Worker worker) {
        if(workerPlaced != player.getWorkerRef().size() - 2) {
            if(player.addWorker(row, col, worker)) {
                workerPlaced++;
            } else {
                //notify
                //TODO: Send error
            }
        } else {
            if(player.addWorker(row, col, worker)) {
                workerPlaced++;
                player.setOldPlayerState(player.getPlaceWorker());
                player.setPlayerState(player.getIdle());
            } else {
                //notify
                //TODO: Send error
            }
        }
    }

    @Override
    public void Move(int row, int col, Worker worker) {

    }

    @Override
    public void Build(int row, int col, Worker worker) {

    }
}
