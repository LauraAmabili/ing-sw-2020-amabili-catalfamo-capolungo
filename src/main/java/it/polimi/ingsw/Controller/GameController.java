
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Observer {


    private Game game = new Game();
    List<String> godChosen = new ArrayList<>(); //metto le divinità scelte dal challenger


    public GameController() {
    }

    @Override
    public void updateInitialiseMatch(){
        game.initialiseMatch();
        game.createTurn();
    }
    @Override
    public void updateNickname(String nickname){

        game.addNickname(nickname);

    }
    @Override
    public void updateChoosingCards(){

        game.chooseCards();

    }
    @Override
    public void updateTryThisCard(String in){

        game.checkAndAdd(in);
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
            game.setGod(godName);
        } else {
            game.GodNotCorrectException();
        }
    }
    /*
    /**
     * God choosen from Challenger
     * @param godName
     */
    /*
    @Override
    public void updateGodAdded(String godName) {

        if(game.getGodListNames().contains(godName)){
            game.addChosenGods(godName);
        }
        else {
            //TODO: gestire
        }




    }
    */
    public void updateAddingWorker(int row, int col, int i){

        game.addingWorker(row, col,i);
    }
    public synchronized void addObserver(ObserverModel view) {
        game.AddObserver(view);
    }
    @Override
    public void updateStartMoving(){

        game.canIMove();

    }
    @Override
    public void updateBuilding(int row, int col, int worker){

        game.building(row, col, worker);

    }
    @Override
    public void updateTryThisWorker(int worker){
        game.checkWorker(worker);
    }
    public void updateMoving(int row, int col, int worker){
        game.moving(row, col, worker);
    }





    /*
    public void initialiseMatch() {
        game.initialiseMatch();
    }
    public void chooseCards() {

        game.chooseCards();
    }
    public void addNickname(String name) {

        game.addNickname(name);
    }
    public void addChosenGods(String godName) {
        game.addChosenGods(godName);
    }
    public void move(){
        game.move();
    }
    public void createTurn() {
        game.createTurn();
    }

     */

}
