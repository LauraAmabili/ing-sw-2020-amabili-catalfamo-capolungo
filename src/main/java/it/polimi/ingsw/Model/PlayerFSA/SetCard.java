package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class SetCard extends PlayerFSA{

    PlayerInterface player;
    Game game;

    public SetCard(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void setCard(String godName) {
        game.setGod(godName);
        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).getNickname().equals(player.getNickname())) {
                game.getOnlinePlayers().get(i).setPlayerState(new Idle(game.getOnlinePlayers().get(i), this, game));;
                player = game.getOnlinePlayers().get(i);
            }
        }
        game.getCurrentTurn().nextTurn();
        if(game.getChosenGods().size() != 0) {
            game.toSetCard();
        } else {
            game.toPlaceWorker();
        }
    }

    @Override
    public void next() {
        player.setPlayerState(new PlaceWorker(player, game));
    }

}
