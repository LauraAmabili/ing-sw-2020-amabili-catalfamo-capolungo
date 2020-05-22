package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.God.GodFileCreator;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.io.IOException;
import java.util.List;

public class Initialized extends PlayerFSA {

    PlayerInterface player;
    Game game;


    public Initialized(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void chosenCard(String godName) throws IOException {
        boolean flag = true;
        if(game.getGodListNames().contains(godName)) {
            if(game.getAvailableGods().isEmpty()) {
                game.getAvailableGods().add(godName);
            } else {
                for(String g : game.getAvailableGods()) {
                    if(g.equals(godName)) {
                        flag = false;
                        game.godNotAdded();
                        break;
                    }
                }
                if(flag) {
                    game.getAvailableGods().add(godName);
                }
            }
            if(game.getAvailableGods().size() == game.getOnlinePlayers().size()) {
                game.godAdded(true);
                for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
                    if (game.getNicknames().get(i).equals(player.getNickname())) {
                        game.getStateList().set(i, new Idle(player, this, game));
                        break;
                    }
                }
                game.getCurrentTurn().nextTurn(game);
                game.toSetCard();
            } else {
                game.godAdded(false);
            }
        } else {
            game.godNotAdded();
        }
    }

    @Override
    public void next() {
        for(int i = 0; i < game.getStateList().size(); i++) {
            if(game.getNicknames().get(i).equals(player.getNickname())) {
                game.getStateList().set(i, new SetCard(player, game));
                break;
            }
        }
    }

}
