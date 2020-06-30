package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;


public class Building extends PlayerFSA {

    final PlayerInterface player;
    final Game game;
    boolean effect;

    public boolean getEffect() {
        return effect;
    }

    public Building(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    /**
     * Check if the current worker can build. if it's not possible player loose.
     * @param worker
     * @param effect
     * @throws IOException
     */
    @Override
    public synchronized void checkBuild(int worker, boolean effect) throws IOException {
        this.effect = effect;
        player.setEnableSpecialBuild(effect);
        if(game.getCurrentTurn().checkLockWorker(game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            game.NoPossibleMoves(player.getNickname());
            game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
            if(game.getOnlinePlayers().size() == 1) {
                game.updateWin(player);
            } else {
                game.getCurrentTurn().nextTurn(game);
            }
        } else {
            game.timeToBuild(worker);
        }
    }

    /**
     * Build a building on the selected coordinates.
     * @param row
     * @param col
     * @param worker
     * @throws IOException
     */
    @Override
    public synchronized void build(int row, int col, int worker) throws IOException {
        if(!player.build(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
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
            game.toMoving();
        }
        player.setEnableSpecialMove(false);
    }

    /**
     * Build a building on the selected coordinates (Used for special effect).
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param worker
     * @throws IOException
     */
    @Override
    public synchronized void build(int row1, int col1, int row2, int col2, int worker) throws IOException {
        if(!player.build(row1 - 1, col1 - 1, row2 - 1, col2 - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            game.NoCoordinatesValidBuildTwoInput(worker);
        } else {
            for(int i = 0; i < game.getStateList().size(); i++) {
                if(game.getNicknames().get(i).equals(player.getNickname())) {
                    game.getStateList().set(i, new Idle(player, this, game));
                    break;
                }
            }
            game.updateBoard();
            game.getCurrentTurn().nextTurn(game);
            game.toMoving();
        }
        player.setEnableSpecialMove(false);
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
