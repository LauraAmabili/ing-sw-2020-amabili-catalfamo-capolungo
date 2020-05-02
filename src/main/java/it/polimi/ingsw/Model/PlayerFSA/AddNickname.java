package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Turn;
import it.polimi.ingsw.Model.Worker;

import java.util.List;
import java.util.Objects;

public class AddNickname extends PlayerFSA {

    PlayerInterface player;
    Game game;

    public AddNickname(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void addNickname(String name) {
        game.addNickname(name);
        player.setPlayerState(new Idle(player, new AddNickname(player, game), game));
    }

    @Override
    public void next() {
        player.setPlayerState(new SetCard(player, game));
    }
}
