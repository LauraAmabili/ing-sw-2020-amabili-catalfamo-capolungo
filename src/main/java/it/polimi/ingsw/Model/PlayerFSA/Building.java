package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;


public class Building extends PlayerFSA {

    PlayerInterface player;
    Game game;

    public Building(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void build(int row, int col, int worker) throws IOException {
        if(!player.build(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker))) {
            game.NoCoordinatesValidBuild(worker);
        } else {
            for(int i = 0; i < game.getStateList().size(); i++) {
                if(game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getStateList().set(i, new Idle(player, this, game));
                    break;
                }
            }
            game.updateBoard();
            game.getCurrentTurn().nextTurn(game);
        }

    }

    @Override
    public void next() {
        for(int i = 0; i < game.getStateList().size(); i++) {
            if(game.getNicknames().get(i).equals(player.getNickname())) {
                game.getStateList().set(i, new Moving(player, game));
                break;
            }
        }
    }

}
