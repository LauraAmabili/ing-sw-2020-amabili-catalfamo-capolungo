package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;


public class Building extends PlayerFSA {

    PlayerInterface player;
    Game game;

    public Building(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void build(int row, int col, int worker) {
        game.building(row, col,worker);
        player.setPlayerState(new Idle(player, this, game));
    }

    @Override
    public void next() {
        player.setPlayerState(new Moving(player, game));
    }

}
