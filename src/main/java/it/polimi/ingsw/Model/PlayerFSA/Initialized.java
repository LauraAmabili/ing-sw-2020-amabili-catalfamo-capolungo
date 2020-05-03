package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class Initialized extends PlayerFSA {

    PlayerInterface player;
    Game game;


    public Initialized(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }



    @Override
    public void chosenCards(String godName) {
        if(game.checkAndAdd(godName)) {
            player.setPlayerState(new Idle(player, this, game));
            game.getCurrentTurn().nextTurn();
            game.toSetCard();
        }
    }

    @Override
    public void next() {
        player.setPlayerState(new SetCard(player, game));
    }

}
