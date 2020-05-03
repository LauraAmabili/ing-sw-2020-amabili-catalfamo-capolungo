package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class PlaceWorker extends PlayerFSA{

    PlayerInterface player;
    Game game;

    public PlaceWorker(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void placeWorker(int row, int col, int worker) {
        if(game.addingWorker(row, col, worker)) {
            player.setPlayerState(new Idle(player, this, game));
            game.getCurrentTurn().nextTurn();
            if(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell() == null) {
                game.toPlaceWorker();
            }
        }
    }

    @Override
    public void next() {
        player.setPlayerState(new Moving(player, game));
    }



}
