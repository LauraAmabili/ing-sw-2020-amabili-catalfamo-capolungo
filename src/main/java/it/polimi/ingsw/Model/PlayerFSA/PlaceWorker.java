package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class PlaceWorker extends PlayerFSA{

    final PlayerInterface player;
    final Game game;

    public PlaceWorker(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
        game.setStarted(true);
    }

    /**
     * Place Worker on the selected coordinates.
     * @param row
     * @param col
     * @param worker
     * @throws IOException
     */
    @Override
    public synchronized void placeWorker(int row, int col, int worker)  {
        if(player.addWorker(row -1 , col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker))) {
            //game.updateBoard();
            if(worker == 0){
                game.BoardWorkerUpdate();
            }
            else {
                game.updateBoard();
            }
            worker++;
        } else {
            game.cellAlreadyOccupied(worker);
        }
        if(worker == game.getCurrentTurn().getCurrentPlayer().getWorkerRef().size()) {
            for (int i = 0; i < game.getStateList().size(); i++) {
                if (game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getStateList().set(i, new Idle(player, this, game));
                    break;
                }
            }
            game.getCurrentTurn().nextTurn(game);
            //game.updateBoard();
            if (game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getCurCell() == null) {
                game.toPlaceWorker();
                return;
            }
            game.toMoving();
        }
    }

    /**
     * Set the next state.
     */
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
