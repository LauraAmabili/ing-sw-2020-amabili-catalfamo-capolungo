
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Observer {


    private Game game = new Game();



    public GameController() {
    }

    public Game getGame() {
        return game;
    }

    @Override
    public synchronized void updateInitialiseMatch(int numberOfPlayers) throws IOException, InterruptedException {

        game.initialiseMatch(numberOfPlayers);
        game.createTurn();

    }

    @Override
    public synchronized void updateNickname(String nickname) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).getNickname() == null) {
                game.getStateList().get(i).addNickname(nickname);
                break;
            }
        }

    }

    @Override
    public synchronized void updateChoosingCards() throws IOException {

        game.chooseCards();

    }

    @Override
    public synchronized void updateTryThisCard(String in) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).chosenCard(in);
        }

    }

    @Override
    public synchronized void updateSetGodName(String godName) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).setCard(godName);
                break;
            }
        }


    }

    @Override
    public synchronized void updateAddingWorker(int row, int col, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).placeWorker(row, col, worker);
                break;
            }
        }

    }

    @Override
    public void updateBuilding(int row, int col, int worker) throws IOException {


        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).build(row, col, worker);
        }

    }

    @Override
    public void updateStartMoving() throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).canIMove();
                break;
            }
        }

    }

    @Override
    public void updateTryThisWorkerEffect(boolean effect, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                if(game.getCurrentTurn().getCurrentPlayer().isHasSpecialMove()) {
                    game.getStateList().get(i).checkWorker(worker, effect);
                } else {
                    game.getStateList().get(i).checkWorker(worker, false);
                }
                break;
            }
        }

    }

    @Override
    public void updatePlayerBuild(boolean effect, String nickname, int worker) throws IOException {
        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                if(game.getCurrentTurn().getCurrentPlayer().isHasSpecialBuild()) {
                    game.getStateList().get(i).checkBuild(worker, effect);
                } else {
                    game.getStateList().get(i).checkBuild(worker, false);
                }
                break;
            }
        }
    }

    @Override
    public void updateTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).build(row1, col1, row2, col2, worker);
                break;
            }
        }
    }

    @Override
    public void updateTryThisWorker(int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).checkWorker(worker, false);
        }

    }

    @Override
    public void updateMoving(int row, int col, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).equals(game.getCurrentTurn().getCurrentPlayer())) {
                game.getStateList().get(i).move(row, col, worker);
                break;
            }
        }



    }

    @Override
    public void updateStartingGame() throws IOException, InterruptedException {
        game.notifyStartingGame();
    }


}
