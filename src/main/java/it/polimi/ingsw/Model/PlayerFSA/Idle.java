package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

public class Idle extends PlayerFSA {

    final PlayerInterface player;
    final PlayerFSA oldState;
    final Game game;

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
