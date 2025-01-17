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
     */
    @Override
    public void canIMove()  {
        int i;
        if(game.getCurrentTurn().checkLockPlayer()) {
            for(i = 0; i < game.getOnlinePlayers().size(); i++) {
                if(player.equals(game.getOnlinePlayers().get(i))) {
                    break;
                }
            }
            game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
            game.NoPossibleMoves(player.getNickname(), game.getOnlinePlayers().get(i).getNickname());
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
     * @param worker worker used
     * @param effect true if effect enabled
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
            game.updateWorkerSelected(worker, player.isHasTwoInputMove(), effect);
        } else {
            game.timeToMove(worker);
        }

    }

    /**
     * Move the worker on the selected coordinates.
     * @param row chosen row
     * @param col chosen col
     * @param worker worker used
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
     * @param row1 chosen row
     * @param col1 chosen col
     * @param row2 chosen row
     * @param col2 chosen col
     * @param worker worker used
     */
    @Override
    public synchronized void move(int row1, int col1, int row2, int col2, int worker) {
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
