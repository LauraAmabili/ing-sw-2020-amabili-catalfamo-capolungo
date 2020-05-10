
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
            game.getStateList().get(i).setCard(godName);
        }
    }

    @Override
    public synchronized void updateAddingWorker(int row, int col, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).placeWorker(row, col, worker);
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
            game.getStateList().get(i).canIMove();
        }

    }

    @Override
    public void updateTryThisWorker(int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).checkWorker(worker);
        }

    }

    @Override
    public void updateMoving(int row, int col, int worker) throws IOException {

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getStateList().get(i).move(row, col, worker);
        }

    }

    @Override
    public void updateStartingGame() throws IOException, InterruptedException {
        game.notifyStartingGame();
    }


}
