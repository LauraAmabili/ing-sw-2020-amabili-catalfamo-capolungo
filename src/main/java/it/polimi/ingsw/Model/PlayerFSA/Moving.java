package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Moving extends PlayerFSA {

    PlayerInterface player;
    Game game;

    public Moving(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void canIMove() {
        game.canIMove();
    }

    @Override
    public void checkWorker(int worker) {
        game.checkWorker(worker);
    }

    @Override
    public void move(int row, int col, int worker) {
        game.moving(row, col, worker);
        player.setPlayerState(new Building(player, game));
    }


}
