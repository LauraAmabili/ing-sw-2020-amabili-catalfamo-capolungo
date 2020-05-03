
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Observer {


    private Game game = new Game();



    public GameController() {
    }

    @Override
    public void updateInitialiseMatch(int numberOfPlayers){

        game.initialiseMatch(numberOfPlayers);
        game.createTurn();
    }

    @Override
    public void updateNickname(String nickname){

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            if(game.getOnlinePlayers().get(i).getNickname() == null) {
                game.getOnlinePlayers().get(i).getPlayerState().addNickname(nickname);
                break;
            }
        }

    }

    @Override
    public void updateChoosingCards(){

        game.chooseCards();

    }
    @Override
    public void updateTryThisCard(String in){

        game.getCurrentTurn().getCurrentPlayer().getPlayerState().chosenCards(in);
        //game.checkAndAdd(in);

    }
    @Override
    public void updateSetGodName(String godName) {

        boolean present = false;
        List<God> godChosenGod = game.getChosenGods();
        for (God god : godChosenGod) {
            if (god.getGodName().equals(godName)) {
                present = true;
                break;
            }
        }
        if (present) {
            game.getCurrentTurn().getCurrentPlayer().getPlayerState().setCard(godName);
        } else {
            game.GodNotCorrectException();
        }
    }
    @Override
    public void updateAddingWorker(int row, int col, int i){

        game.getCurrentTurn().getCurrentPlayer().getPlayerState().placeWorker(row, col,i);

    }
    @Override
    public void updateStartMoving(){

        game.getCurrentTurn().getCurrentPlayer().getPlayerState().canIMove();

    }
    @Override
    public void updateWantToBuild(int worker){

        game.askingToBuild(worker);

    }
    @Override
    public void updateBuilding(int row, int col, int worker){

        game.getCurrentTurn().getCurrentPlayer().getPlayerState().build(row, col, worker);

    }
    @Override
    public void updateTryThisWorker(int worker){

        game.getCurrentTurn().getCurrentPlayer().getPlayerState().checkWorker(worker);
    }
    @Override
    public void updateMoving(int row, int col, int worker){

        game.getCurrentTurn().getCurrentPlayer().getPlayerState().move(row, col, worker);
    }

    public synchronized void addObserver(ObserverModel view) {
        game.AddObserver(view);
    }







}
