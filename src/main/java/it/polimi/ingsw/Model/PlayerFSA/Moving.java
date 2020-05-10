package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class Moving extends PlayerFSA {

    PlayerInterface player;
    Game game;

    public Moving(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void canIMove() throws IOException {
        if(game.getCurrentTurn().checkLockPlayer()) {
            game.NoPossibleMoves(player.getNickname());
            game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
            if(game.getOnlinePlayers().size() == 1) {
                game.updateWin(player);
            } else {
                game.getCurrentTurn().nextTurn(game);
            }
        } else {
            game.timeToCheckWorker();
        }
    }

    @Override
    public void checkWorker(int worker) throws IOException {
        if(game.getCurrentTurn().checkLockWorker(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            if(worker == 2) {
                worker = 1;
            } else {
                worker++;
            }
            game.updateWorkerSelected(worker);
        }
        game.timeToMove(worker);
    }

    @Override
    public void move(int row, int col, int worker) throws IOException {
        if(!player.move(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            game.NoCoordinatesValidMove(worker);
        } else {
            for (int i = 0; i < game.getStateList().size(); i++) {
                if(game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getStateList().set(i, new Building(player, game));
                    break;
                }
            }
            game.updateBoard(row, col, worker);
            if(game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
                game.updateWin(player);
            } else {
                game.timeToBuild(worker);
            }
        }
    }


}
