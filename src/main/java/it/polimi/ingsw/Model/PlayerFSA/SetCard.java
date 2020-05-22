package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.PlayerCreator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class SetCard extends PlayerFSA{

    PlayerInterface player;
    final Game game;

    public SetCard(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void setCard(String godName) throws IOException {
        PlayerCreator playerCreator = new PlayerCreator();
        if(!game.getChosenGods().contains(godName)) {
            game.NoGodHasSuchName();
        } else {
            game.getChosenGods().remove(godName);
            player.setActiveCard(new God(godName));
            player = playerCreator.createPlayer(godName, player);
            game.getCurrentTurn().setCurrentPlayer(player);
            for(int i= 0; i < game.getCurrentTurn().getActivePlayers().size(); i++){
                if(game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getCurrentTurn().getActivePlayers().set(i, player);
                    game.getOnlinePlayers().set(i, player);
                    break;
                }
            }
            game.msgGodSet(godName);
            for(int i = 0; i < game.getStateList().size(); i++) {
                if(game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getStateList().set(i, new Idle(player, this, game));
                    break;
                }
            }
            game.getCurrentTurn().nextTurn(game);

            if(game.getChosenGods().size() != 0) {
                game.toSetCard();
            } else {
                game.toPlaceWorker();
            }


        }
    }

    @Override
    public void next() {
        for(int i = 0; i < game.getStateList().size(); i++) {
            if(game.getNicknames().get(i).equals(player.getNickname())) {
                game.getStateList().set(i, new PlaceWorker(player, game));
                break;
            }
        }
    }

}
