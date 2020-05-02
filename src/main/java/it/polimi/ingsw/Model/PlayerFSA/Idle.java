package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Idle extends PlayerFSA {

    PlayerInterface player;
    PlayerFSA oldState;
    Game game;

    public Idle(PlayerInterface player, PlayerFSA oldState, Game game) {
        this.player = player;
        this.oldState = oldState;
        this.game = game;
    }


    @Override
    public void next() {
        oldState.next();
    }
}
