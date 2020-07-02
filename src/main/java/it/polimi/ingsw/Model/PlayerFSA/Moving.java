package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;

public class Moving extends PlayerFSA {

    final PlayerInterface player;
    final Game game;
    boolean effect;

    public Moving(PlayerInterface player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public boolean getEffect() {
        return effect;
    }

    /**
     * Check if both workers can move. if both can't move delete this player
     * @throws IOException
     */
    @Override
    public void canIMove()  {
        if(game.getCurrentTurn().checkLockPlayer()) {
            game.NoPossibleMoves(player.getNickname());
            game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
            if(game.getOnlinePlayers().size() == 1) {
                game.updateWin(game.getOnlinePlayers().get(0));
            } else {
                game.getCurrentTurn().nextTurn(game);
            }
        } else {
            game.timeToCheckWorker();
        }
    }

    /**
     * Check if the selected worker can move. if it's not possible the other worker is chosen.
     * @param worker
     * @param effect
     * @throws IOException
     */
    @Override
    public synchronized void checkWorker(int worker, boolean effect) {

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

    /**
     * Move the worker on the selected coordinates.
     * @param row
     * @param col
     * @param worker
     * @throws IOException
     */
    @Override
    public synchronized void move(int row, int col, int worker)  {
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

    /**
     * Move the worker on the selected coordinates (Used for special effects)
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param worker
     * @throws IOException
     */
    @Override
    public synchronized void move(int row1, int col1, int row2, int col2, int worker){
        if(!player.move(row1 - 1, col1 - 1, row2 -1, col2 - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            game.NoCoordinatesValidMoveTwoInput(worker);
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
