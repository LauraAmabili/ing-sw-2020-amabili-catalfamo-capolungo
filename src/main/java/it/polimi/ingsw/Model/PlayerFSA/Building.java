package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;




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
     */
    @Override
    public synchronized void checkBuild(int worker, boolean effect)  {
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
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     */
    @Override
    public synchronized void build(int row, int col, int worker)  {
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
     * @param row1 chosen row
     * @param col1 chosen col
     * @param row2 chosen row
     * @param col2 chosen col
     * @param worker number of the worker used
     */
    @Override
    public synchronized void build(int row1, int col1, int row2, int col2, int worker)  {
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
