package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class Moving extends PlayerFSA {

    PlayerInterface player;
    Game game;
    boolean effect;

    public Moving(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public boolean getEffect() {
        return effect;
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
    public void checkWorker(int worker, boolean effect) throws IOException {
        this.effect = effect;
        player.setEnableSpecialMove(effect);
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
            game.updateBoard();
            if(game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
                game.updateWin(player);
            } else {
                player.setEnableSpecialMove(false);
                game.askEffectBuild(worker);
            }
        }
    }

    @Override
    public void move(int row1, int col1, int row2, int col2, int worker) throws IOException {
        if(!player.move(row1 - 1, col1 - 1, row2 -1, col2 - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            game.NoCoordinatesValidMove(worker);
        } else {
            for (int i = 0; i < game.getStateList().size(); i++) {
                if(game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getStateList().set(i, new Building(player, game));
                    break;
                }
            }
            game.updateBoard();
            if(game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
                game.updateWin(player);
            } else {
                player.setEnableSpecialMove(false);
                game.askEffectBuild(worker);
            }
        }
    }

}
